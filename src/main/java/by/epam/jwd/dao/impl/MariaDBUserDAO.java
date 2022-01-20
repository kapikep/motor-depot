package by.epam.jwd.dao.impl;

import java.sql.*;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.dao.connection_pool.MariaDBConnectionPool;
import by.epam.jwd.dao.interf.UserDao;
import by.epam.jwd.entity.Role;
import by.epam.jwd.entity.Status;
import by.epam.jwd.entity.User;

public class MariaDBUserDAO implements UserDao{
	
	private final MariaDBConnectionPool connectionPool = MariaDBConnectionPool.getConnectionPool();
	private static final String AUTHORIZATION = "SELECT * FROM users WHERE login=? AND";

	@Override
	public User authorization(String login, String password) throws DAOException {

		User user = null;

		try {
			ResultSet rs;
			Connection connection = connectionPool.takeConnection();
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE login=? AND password=?");
			ps.setString(1, login);
			ps.setString(2, password);

			rs = ps.executeQuery();

			if (rs.next()) {
				user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getString("login"),
						rs.getString("password"), rs.getDouble("phone_number"), rs.getString("photo"), Status.valueOf(rs.getString("status")),
						rs.getString("e-mail"), Role.getRole(rs.getInt("roles_id")));
			}
				//userID = resultSet.getInt(ID);
		
			//return userID;


			connectionPool.returnConnection(connection, ps, rs);

	} catch (SQLException e) {
		throw new DAOException(e);
	}
	return user;
}

	@Override
	public boolean createUser(User user) throws DAOException {
		
		return false;
	}

	@Override
	public User findUser(String login) throws DAOException {
		return null;
	}

	@Override
	public void updateUser(User user) throws DAOException {

	}

}
