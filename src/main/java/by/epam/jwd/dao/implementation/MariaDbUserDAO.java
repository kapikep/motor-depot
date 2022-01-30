package by.epam.jwd.dao.implementation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.dao.connection_pool.MariaDBConnectionPool;
import by.epam.jwd.dao.interf.UserDao;
import by.epam.jwd.entity.Role;
import by.epam.jwd.entity.Status;
import by.epam.jwd.entity.User;

public class MariaDbUserDAO implements UserDao {

    private final MariaDBConnectionPool CONNECTION_POOL = MariaDBConnectionPool.getConnectionPool();

    @Override
    public void createUser(User user) throws DAOException {
        try {
            Connection connection = CONNECTION_POOL.takeConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO users (" +
                    "name, surname, login, password, phone_number, photo, status, `e-mail`, additionalInfo, roles_id)" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            initPrepStatement(user, ps);

            ps.executeUpdate();
            CONNECTION_POOL.returnConnection(connection, ps);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public User readUser(int id) throws DAOException {
        User user = null;
        try {
            Connection connection = CONNECTION_POOL.takeConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE id=?");
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                user = buildUser(resultSet);
            }
            CONNECTION_POOL.returnConnection(connection, ps, resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return user;
    }

    @Override
    public List<User> readUsers() throws DAOException {
        List<User> users = new ArrayList<>();
        try {
            Connection connection = CONNECTION_POOL.takeConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                users.add(buildUser(resultSet));
            }
            CONNECTION_POOL.returnConnection(connection, ps, resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return users;
    }

    @Override
    public List<User> readUsers(int page, int limit) throws DAOException {
        List<User> users = new ArrayList<>();
        try {
            Connection connection = CONNECTION_POOL.takeConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM users LIMIT ? OFFSET ?");

            int offset = (page - 1) * limit;
            ps.setInt(1, limit);
            ps.setInt(2, offset);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                users.add(buildUser(resultSet));
            }
            CONNECTION_POOL.returnConnection(connection, ps, resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return users;
    }

    @Override
    public List<User> findUsers(int page, int limit, String whereParam, String whereValue) throws DAOException {
        List<User> users = new ArrayList<>();
        try {
            Connection connection = CONNECTION_POOL.takeConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE "
                    + whereParam + "=?" + " LIMIT ? OFFSET ?");

            int offset = (page - 1) * limit;
            ps.setString(1, whereValue);
            ps.setInt(2, limit);
            ps.setInt(3, offset);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                users.add(buildUser(resultSet));
            }
            CONNECTION_POOL.returnConnection(connection, ps, resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return users;
    }

    @Override
    public List<User> readUsers(int page, int limit, String orderBy) throws DAOException {
        List<User> users = new ArrayList<>();
        try {
            Connection connection = CONNECTION_POOL.takeConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM users ORDER BY " + orderBy + " LIMIT ? OFFSET ?");

            int offset = (page - 1) * limit;
            ps.setInt(1, limit);
            ps.setInt(2, offset);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                users.add(buildUser(resultSet));
            }
            CONNECTION_POOL.returnConnection(connection, ps, resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return users;
    }

    @Override
    public List<User> findUsers(String whereParam, String whereValue) throws DAOException {
        List<User> users = new ArrayList<>();
        try {
            Connection connection = CONNECTION_POOL.takeConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE " + whereParam + "=?");
            ps.setString(1, whereValue);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                users.add(buildUser(resultSet));
            }
            CONNECTION_POOL.returnConnection(connection, ps, resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return users;
    }

    @Override
    public List<User> findUsers(Map<String, String> criteriaMap) throws DAOException {
        List<User> users = new ArrayList<>();
        try {
            Connection connection = CONNECTION_POOL.takeConnection();
            Statement st = connection.createStatement();
            StringBuilder s = new StringBuilder("SELECT * FROM users WHERE ");

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
                users.add(buildUser(resultSet));
            }
            CONNECTION_POOL.returnConnection(connection, st, resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return users;
    }

    @Override
    public int getUsersSize() throws DAOException {
        int size = 0;
        try {
            Connection connection = CONNECTION_POOL.takeConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM users");
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
    public User authorization(String login, String password) throws DAOException {
        User user = null;
        try {
            ResultSet rs;
            Connection connection = CONNECTION_POOL.takeConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE login=? AND password=?");
            ps.setString(1, login);
            ps.setString(2, password);

            rs = ps.executeQuery();

            if (rs.next()) {
                user = buildUser(rs);
            }
            CONNECTION_POOL.returnConnection(connection, ps, rs);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return user;
    }

    @Override
    public void updateUser(User user) throws DAOException {
        try {
            Connection connection = CONNECTION_POOL.takeConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE users SET name=?, surname=?, login=?, password=?, " +
                    "phone_number=?, photo=?, status=?, `e-mail`=?, additionalInfo=?, roles_id=? WHERE id=?");
            initPrepStatement(user, ps);
            ps.setInt(11, user.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    private void initPrepStatement(User user, PreparedStatement ps) throws SQLException {
        ps.setString(1, user.getName());
        ps.setString(2, user.getSurname());
        ps.setString(3, user.getLogin());
        ps.setString(4, user.getPassword());
        ps.setString(5, user.getPhoneNumber());
        ps.setString(6, user.getPhoto());
        ps.setString(7, user.getStatus().toString());
        ps.setString(8, user.geteMail());
        ps.setString(9, user.getAdditionalInfo());
        ps.setInt(10, user.getRole().getId());
    }

    private User buildUser(ResultSet rs) throws SQLException {
        return new User(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getString("login"),
                rs.getString("password"), rs.getString("phone_number"), rs.getString("photo"), Status.valueOf(rs.getString("status")),
                rs.getString("e-mail"), rs.getString("additionalInfo"), Role.getRole(rs.getInt("roles_id")));
    }
}
