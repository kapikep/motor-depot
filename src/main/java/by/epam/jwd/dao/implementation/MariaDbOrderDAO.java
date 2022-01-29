package by.epam.jwd.dao.implementation;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.dao.connection_pool.MariaDBConnectionPool;
import by.epam.jwd.dao.interf.OrderDAO;
import by.epam.jwd.entity.Car;
import by.epam.jwd.entity.Order;
import by.epam.jwd.entity.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MariaDbOrderDAO implements OrderDAO {

    private final MariaDBConnectionPool CONNECTION_POOL = MariaDBConnectionPool.getConnectionPool();

    @Override
    public void createOrder(Order order) throws DAOException{
        try {
            Connection connection = CONNECTION_POOL.takeConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO orders (criteria, request_date, depart_place, arrival_place, start_date, end_date, " +
                    "order_status, travel_distance, total_amount, payment_status, client_full_name, client_phone, client_id, cars_id, driver_id, admin_id)" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, order.getCriteria());
            ps.setTimestamp(2, new Timestamp(order.getRequestDate().getTime()));
            ps.setString(3, order.getDepartPlace());
            ps.setString(4, order.getDepartPlace());
            ps.setString(5, order.getArrivalPlace());
            ps.setString(6, order.getArrivalPlace());
            ps.setString(7, order.getArrivalPlace());
            ps.setInt(8, order.getDistance());
            ps.setInt(9, order.getTotalAmount());
            ps.setString(10, order.getPaymentStatus());
            ps.setString(11, order.getClientFullName());
            ps.setString(12, order.getClientPhone());
            ps.setInt(13, order.getClientId());
            ps.setInt(14, order.getCarId());
            ps.setInt(15, order.getDriverId());
            ps.setInt(16, order.getAdminId());
            ps.executeUpdate();
            CONNECTION_POOL.returnConnection(connection, ps);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createNotApproveOrder(String fullName, String phoneNumber, String criteria, Date requestDate) throws DAOException{
        try {
            Connection connection = CONNECTION_POOL.takeConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO orders (criteria, order_status, client_full_name, client_phone, request_date)" +
                    "VALUES(?, ?, ?, ?, ?)");
            ps.setString(1, criteria);
            ps.setString(2, Status.NOT_APPROVE.toString());
            ps.setString(3, fullName);
            ps.setString(4, phoneNumber);
            ps.setTimestamp(5, new Timestamp(requestDate.getTime()));
            ps.executeUpdate();
            CONNECTION_POOL.returnConnection(connection, ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void createNotApproveOrder(Order order) throws DAOException{
        try {
            Connection connection = CONNECTION_POOL.takeConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO orders (criteria, request_date, depart_place, arrival_place, start_date, " +
                    "order_status, client_full_name, client_phone)" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, order.getCriteria());
            ps.setTimestamp(2, new Timestamp(order.getRequestDate().getTime()));
            ps.setString(3, order.getDepartPlace());
            ps.setString(4, order.getArrivalPlace());
            ps.setTimestamp(5, new Timestamp(order.getRequestDate().getTime()));
            ps.setString(6, Status.NOT_APPROVE.toString());
            ps.setString(7, order.getClientFullName());
            ps.setString(8, order.getClientPhone());
            ps.executeUpdate();
            CONNECTION_POOL.returnConnection(connection, ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Order> findOrders(String param, String value) throws DAOException {
        List<Order> orders = new ArrayList<>();
        try {
            Connection connection = CONNECTION_POOL.takeConnection();
            String str = "SELECT * FROM orders LEFT JOIN cars c on c.id = orders.cars_id " +
                    "LEFT JOIN users u on u.id = orders.client_id " +
                    "LEFT JOIN users u2 on u2.id = orders.driver_id " +
                    "LEFT JOIN users u3 on u3.id = orders.admin_id WHERE " + param + "=?";
            PreparedStatement ps = connection.prepareStatement(str);

            ps.setString(1, value);

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                orders.add(buildOrder(resultSet));
            }
            CONNECTION_POOL.returnConnection(connection, ps, resultSet);

        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return orders;
    }

    @Override
    public List<Order> findOrders(Map<String, String> criteriaMap) throws DAOException {
        List<Order> orders = new ArrayList<>();
        try {
            Connection connection = CONNECTION_POOL.takeConnection();
            Statement st = connection.createStatement();
            StringBuilder s = new StringBuilder("SELECT * FROM orders JOIN cars c on c.id = orders.cars_id " +
                    "LEFT JOIN users u on u.id = orders.client_id " +
                    "LEFT JOIN users u2 on u2.id = orders.driver_id " +
                    "LEFT JOIN users u3 on u3.id = orders.admin_id WHERE ");

            for (Map.Entry<String, String> entry : criteriaMap.entrySet()) {
                String str = entry.getValue();
                if(str.matches("(\\>|\\<|(\\<=)|(\\>=))\\d+")){
                    s.append(entry.getKey());
                    s.append(entry.getValue());
                    s.append(" AND ");
                }else {
                    s.append(entry.getKey());
                    s.append("='");
                    s.append(entry.getValue());
                    s.append("' AND ");
                }
            }
            String res = s.substring(0, s.length() - 5);
            ResultSet resultSet = st.executeQuery(res);
            while (resultSet.next()) {
                orders.add(buildOrder(resultSet));
            }
            CONNECTION_POOL.returnConnection(connection, st, resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return orders;
    }

    @Override
    public Order readOrder(int id) throws DAOException {
        Order order = null;
        try {
            Connection connection = CONNECTION_POOL.takeConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM orders LEFT JOIN cars c on c.id = orders.cars_id " +
                    "LEFT JOIN users u on u.id = orders.client_id " +
                    "LEFT JOIN users u2 on u2.id = orders.driver_id " +
                    "LEFT JOIN users u3 on u3.id = orders.admin_id WHERE orders.id=?");
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                order = buildOrder(resultSet);
            }
            CONNECTION_POOL.returnConnection(connection, ps, resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return order;
    }

    @Override
    public List<Order> readOrders() throws DAOException {
        List<Order> cars = new ArrayList<>();

        try {
            Connection connection = CONNECTION_POOL.takeConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM orders LEFT JOIN cars c on c.id = orders.cars_id " +
                            "LEFT JOIN users u on u.id = orders.client_id " +
                            "LEFT JOIN users u2 on u2.id = orders.driver_id " +
                            "LEFT JOIN users u3 on u3.id = orders.admin_id");
            while (resultSet.next()) {
                cars.add(buildOrder(resultSet));
            }
            CONNECTION_POOL.returnConnection(connection, statement, resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return cars;
    }

    @Override
    public List<Order> readOrders(String whereParam, String whereValue) throws DAOException {
        List<Order> cars = new ArrayList<>();

        try {
            Connection connection = CONNECTION_POOL.takeConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders LEFT JOIN cars c on c.id = orders.cars_id " +
                    "LEFT JOIN users u on u.id = orders.client_id " +
                    "LEFT JOIN users u2 on u2.id = orders.driver_id " +
                    "LEFT JOIN users u3 on u3.id = orders.admin_id WHERE " + whereParam + "=?");
            statement.setString(1, whereValue);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                cars.add(buildOrder(resultSet));
            }
            CONNECTION_POOL.returnConnection(connection, statement, resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return cars;
    }

    @Override
    public List<Order> readOrders(int page, int limit) throws DAOException {
        List<Order> orders = new ArrayList<>();

        try {
            Connection connection = CONNECTION_POOL.takeConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders LEFT JOIN cars c on c.id = orders.cars_id " +
                    "LEFT JOIN users u on u.id = orders.client_id " +
                    "LEFT JOIN users u2 on u2.id = orders.driver_id " +
                    "LEFT JOIN users u3 on u3.id = orders.admin_id ORDER BY orders.id LIMIT ? OFFSET ?");
            int offset = (page - 1) * limit;
            statement.setInt(1, limit);
            statement.setInt(2,offset);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                orders.add(buildOrder(resultSet));
            }
            CONNECTION_POOL.returnConnection(connection, statement, resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return orders;
    }

    @Override
    public List<Order> readOrders(int page, int limit, String whereParam, String whereValue) throws DAOException {
        List<Order> orders = new ArrayList<>();

        try {
            Connection connection = CONNECTION_POOL.takeConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders LEFT JOIN cars c on c.id = orders.cars_id " +
                    "LEFT JOIN users u on u.id = orders.client_id " +
                    "LEFT JOIN users u2 on u2.id = orders.driver_id " +
                    "LEFT JOIN users u3 on u3.id = orders.admin_id WHERE " + whereParam + "=?" +" LIMIT ? OFFSET ?");
            int offset = (page - 1) * limit;
            statement.setString(1, whereValue);
            statement.setInt(2, limit);
            statement.setInt(3,offset);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                orders.add(buildOrder(resultSet));
            }
            CONNECTION_POOL.returnConnection(connection, statement, resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return orders;
    }

    @Override
    public List<Order> readOrders(int page, int limit, String orderBy) throws DAOException {
        List<Order> orders = new ArrayList<>();

        try {
            Connection connection = CONNECTION_POOL.takeConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders LEFT JOIN cars c on c.id = orders.cars_id " +
                    "LEFT JOIN users u on u.id = orders.client_id " +
                    "LEFT JOIN users u2 on u2.id = orders.driver_id " +
                    "LEFT JOIN users u3 on u3.id = orders.admin_id ORDER BY ? LIMIT ? OFFSET ?");
            int offset = (page - 1) * limit;
            statement.setInt(1, limit);
            statement.setInt(2,offset);
            statement.setString(3,orderBy);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                orders.add(buildOrder(resultSet));
            }
            CONNECTION_POOL.returnConnection(connection, statement, resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return orders;
    }

    @Override
    public int getOrderSize() throws DAOException{
        int size = 0;
        try {
            Connection connection = CONNECTION_POOL.takeConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM orders");
            while (resultSet.next()) {
                size = resultSet.getInt("COUNT(*)");
            }
            CONNECTION_POOL.returnConnection(connection, statement, resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return size;
    }

    @Override
    public void updateOrder(Car car) throws DAOException {
        try {
            Connection connection = CONNECTION_POOL.takeConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE cars SET licence_plate=?, color=?, car_photo=?, odometr=?, status=?, car_model_id=? WHERE id=?");
            ps.setString(1, car.getLicencePlate());
            ps.setString(2, car.getColor());
            ps.setString(3, car.getPhoto());
            ps.setInt(4, car.getOdometr());
            ps.setString(5, car.getStatus());
            ps.setInt(6, car.getCarModelId());
            ps.setInt(7, car.getId());
            ps.executeUpdate();
            CONNECTION_POOL.returnConnection(connection, ps);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void deleteOrder(int id) throws DAOException {
        try {
            int count;
            Connection connection = CONNECTION_POOL.takeConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM orders WHERE id=?");
            ps.setInt(1, id);
            count = ps.executeUpdate();
            CONNECTION_POOL.returnConnection(connection, ps);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    private Order buildOrder(ResultSet rs) throws SQLException{
        return new Order(rs.getInt("id"), rs.getString("criteria"), rs.getTimestamp("request_date"), rs.getString("depart_place"),
                rs.getString("arrival_place"), rs.getTimestamp("start_date"), rs.getTimestamp("end_date"), rs.getString("order_status"),
                rs.getInt("travel_distance"), rs.getInt("total_amount"), rs.getString("payment_status"), rs.getInt("client_id"),
                rs.getString("client_full_name"), rs.getString("client_phone"), rs.getInt("cars_id"), rs.getString("licence_plate"), rs.getInt("driver_id"),
                rs.getString("u2.name"), rs.getString("u2.surname"), rs.getInt("admin_id"),
                rs.getString("u3.name"), rs.getString("u3.surname"));
    }
}
