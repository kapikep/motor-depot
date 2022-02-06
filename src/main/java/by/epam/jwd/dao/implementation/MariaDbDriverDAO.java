package by.epam.jwd.dao.implementation;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.dao.MotorDepotDAOFactory;
import by.epam.jwd.dao.connection_pool.MariaDBConnectionPool;
import by.epam.jwd.dao.interf.DriverDAO;
import by.epam.jwd.dao.interf.UserDao;
import by.epam.jwd.entity.Driver;
import by.epam.jwd.entity.Role;
import by.epam.jwd.entity.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MariaDbDriverDAO implements DriverDAO {

    private final MariaDBConnectionPool CONNECTION_POOL = MariaDBConnectionPool.getConnectionPool();

    @Override
    public void createDriver(Driver driver) throws DAOException {
        UserDao userDAO = MotorDepotDAOFactory.getMotorDepotDAOFactory().getUserDao();
        int userId;
        try {
            Connection connection = CONNECTION_POOL.takeConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO drivers_details " +
                    "(category, driving_experience, date_of_employment, date_of_dismissal, attached_car_id, user_id)" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?)");
            initPrepStatement(driver, ps);
            userDAO.createUser(driver);
            userId = userDAO.authorization(driver.getLogin(), driver.getPassword()).getId();
            ps.setInt(6, userId);
            ps.executeUpdate();
            CONNECTION_POOL.returnConnection(connection, ps);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public Driver readDriver(int id) throws DAOException {
        Driver driver = null;
        try {
            Connection connection = CONNECTION_POOL.takeConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM drivers_details " +
                             "LEFT JOIN cars c on c.id = drivers_details.attached_car_id " +
                             "JOIN users u on u.id = drivers_details.user_id WHERE drivers_details.id=?");
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                driver = buildDriver(resultSet);
            }
            CONNECTION_POOL.returnConnection(connection, ps, resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return driver;
    }

    @Override
    public List<Driver> readDrivers() throws DAOException {
        List<Driver> drivers = new ArrayList<>();
        try {
            Connection connection = CONNECTION_POOL.takeConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM drivers_details " +
                    "LEFT JOIN cars c on c.id = drivers_details.attached_car_id " +
                    "JOIN users u on u.id = drivers_details.user_id");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                drivers.add(buildDriver(rs));
            }
            CONNECTION_POOL.returnConnection(connection, ps, rs);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return drivers;
    }

    @Override
    public List<Driver> readDrivers(int page, int limit) throws DAOException {
        List<Driver> drivers = new ArrayList<>();
        try {
            Connection connection = CONNECTION_POOL.takeConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM drivers_details " +
                    "LEFT JOIN cars c on c.id = drivers_details.attached_car_id " +
                    "JOIN users u on u.id = drivers_details.user_id LIMIT ? OFFSET ?");

            int offset = (page - 1) * limit;
            ps.setInt(1, limit);
            ps.setInt(2, offset);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                drivers.add(buildDriver(resultSet));
            }
            CONNECTION_POOL.returnConnection(connection, ps, resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return drivers;
    }

    @Override
    public List<Driver> findDrivers(int page, int limit, String whereParam, String whereValue) throws DAOException {
        List<Driver> drivers = new ArrayList<>();
        try {
            Connection connection = CONNECTION_POOL.takeConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM drivers_details " +
                    "LEFT JOIN cars c on c.id = drivers_details.attached_car_id " +
                    "JOIN users u on u.id = drivers_details.user_id " + whereParam + "=?" + " LIMIT ? OFFSET ?");

            int offset = (page - 1) * limit;
            ps.setString(1, whereValue);
            ps.setInt(2, limit);
            ps.setInt(3, offset);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                drivers.add(buildDriver(resultSet));
            }
            CONNECTION_POOL.returnConnection(connection, ps, resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return drivers;
    }

    @Override
    public List<Driver> readDrivers(int page, int limit, String orderBy) throws DAOException {
        List<Driver> drivers = new ArrayList<>();
        try {
            Connection connection = CONNECTION_POOL.takeConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM drivers_details " +
                            "LEFT JOIN cars c on c.id = drivers_details.attached_car_id " +
                            "JOIN users u on u.id = drivers_details.user_id ORDER BY " + orderBy + " LIMIT ? OFFSET ?");

            int offset = (page - 1) * limit;
            ps.setInt(1, limit);
            ps.setInt(2, offset);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                drivers.add(buildDriver(resultSet));
            }
            CONNECTION_POOL.returnConnection(connection, ps, resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return drivers;
    }

    @Override
    public List<Driver> findDrivers(String whereParam, String whereValue) throws DAOException {
        List<Driver> Drivers = new ArrayList<>();
        try {
            Connection connection = CONNECTION_POOL.takeConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM drivers_details " +
                    "LEFT JOIN cars c on c.id = drivers_details.attached_car_id " +
                    "JOIN users u on u.id = drivers_details.user_id WHERE " + whereParam + " LIKE ?");
            ps.setString(1, whereValue);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Drivers.add(buildDriver(resultSet));
            }
            CONNECTION_POOL.returnConnection(connection, ps, resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return Drivers;
    }

    @Override
    public List<Driver> findDrivers(Map<String, String> criteriaMap) throws DAOException {
        List<Driver> drivers = new ArrayList<>();
        try {
            Connection connection = CONNECTION_POOL.takeConnection();
            Statement st = connection.createStatement();
            StringBuilder s = new StringBuilder("SELECT * FROM drivers_details " +
                    "LEFT JOIN cars c on c.id = drivers_details.attached_car_id " +
                    "JOIN users u on u.id = drivers_details.user_id WHERE ");

            for (Map.Entry<String, String> entry : criteriaMap.entrySet()) {
                String str = entry.getValue();
                if (str.matches("(\\>|\\<|(\\<=)|(\\>=))\\d+")) {
                    s.append(entry.getKey());
                    s.append(entry.getValue());
                    s.append(" AND ");
                } else {
                    s.append(entry.getKey());
                    s.append("='");
                    s.append(entry.getValue());
                    s.append("' AND ");
                }
            }

            String res = s.substring(0, s.length() - 5);
            ResultSet resultSet = st.executeQuery(res);
            while (resultSet.next()) {
                drivers.add(buildDriver(resultSet));
            }
            CONNECTION_POOL.returnConnection(connection, st, resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return drivers;
    }

    @Override
    public int getDriversSize() throws DAOException {
        int size = 0;
        try {
            Connection connection = CONNECTION_POOL.takeConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM drivers_details");
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
    public void updateDriver(Driver driver) throws DAOException {
        try {
            Connection connection = CONNECTION_POOL.takeConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE drivers_details SET " +
                    "category=?, driving_experience=?, date_of_employment=?, date_of_dismissal=?, attached_car_id=? WHERE id=?");

            initPrepStatement(driver, ps);
            ps.setInt(6, driver.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    private void initPrepStatement(Driver driver, PreparedStatement ps) throws SQLException {
        ps.setString(1, driver.getCategory());
        ps.setInt(2, driver.getDrivingExperience());
        Date dateOfEmployment = driver.getDateOfEmployment();

        if(dateOfEmployment != null){
            ps.setTimestamp(3, new Timestamp(driver.getDateOfEmployment().getTime()));
        }else {
            ps.setTimestamp(3, null);
        }

        Date dateOfDismissal = driver.getDateOfDismissal();
        if(dateOfDismissal != null) {
            ps.setTimestamp(4, new Timestamp(driver.getDateOfDismissal().getTime()));
        }else {
            ps.setTimestamp(4, null);
        }
        ps.setInt(5, driver.getAttachedCarId());
    }

    private Driver buildDriver(ResultSet rs) throws SQLException {
        return new Driver(rs.getInt("drivers_details.id"), rs.getString("name"), rs.getString("surname"), rs.getString("login"),
                rs.getString("password"), rs.getString("phone_number"), rs.getString("photo"), Status.valueOf(rs.getString("u.status")),
                rs.getString("e-mail"), rs.getString("additionalInfo"), Role.getRole(rs.getInt("roles_id")), rs.getInt("u.id"),
                rs.getString("category"), rs.getInt("driving_experience"), rs.getTimestamp("date_of_employment"), rs.getTimestamp("date_of_dismissal"),
                rs.getInt("attached_car_id"), rs.getString("c.licence_plate"));
    }
}
