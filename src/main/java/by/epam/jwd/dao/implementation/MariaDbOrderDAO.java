package by.epam.jwd.dao.implementation;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.dao.connection_pool.MariaDBConnectionPool;
import by.epam.jwd.dao.interf.OrderDAO;
import by.epam.jwd.entity.Order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MariaDbOrderDAO implements OrderDAO {

    private final MariaDBConnectionPool CONNECTION_POOL = MariaDBConnectionPool.getConnectionPool();
    private static final List<String> orderParam = Arrays.asList("id", "criteria", "request_date", "depart_place", "start_date",
            "end_date", "order_status", "travel_distance", "total_amount", "payment_status", "client_id", "cars_id", "driver_id");


    @Override
    public List<Order> readAllOrders() throws DAOException {
        List<Order> cars = new ArrayList<>();

        try {
            Connection connection = CONNECTION_POOL.takeConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM orders JOIN cars c on c.id = orders.cars_id " +
                            "JOIN users u on u.id = orders.client_id " +
                            "JOIN users u2 on u2.id = orders.driver_id " +
                            "JOIN users u3 on u3.id = orders.admin_id");
            while (resultSet.next()) {
                cars.add(buildOrder(resultSet));
            }

            CONNECTION_POOL.returnConnection(connection, statement, resultSet);

        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return cars;
    }

    private Order buildOrder(ResultSet rs) throws SQLException{
        //id, criteria, request_date, depart_place, arrival_place, start_date, end_date, order_status, travel_distance, total_amount, payment_status, client_id, cars_id, driver_id
        return new Order(rs.getInt("id"), rs.getString("criteria"), rs.getDate("request_date"), rs.getString("depart_place"),
                rs.getString("arrival_place"), rs.getDate("start_date"), rs.getDate("end_date"), rs.getString("order_status"),
                rs.getString("travel_distance"), rs.getString("total_amount"), rs.getString("payment_status"), rs.getInt("client_id"),
                rs.getString("name"), rs.getString("surname"), rs.getInt("cars_id"), rs.getString("licence_plate"), rs.getInt("driver_id"),
                rs.getString("u2.name"), rs.getString("u2.surname"), rs.getInt("admin_id"),
                rs.getString("u3.name"), rs.getString("u3.surname"));
    }
}
