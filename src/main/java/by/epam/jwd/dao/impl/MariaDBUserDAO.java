package by.epam.jwd.dao.impl;

import java.sql.*;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.dao.connection_pool.MariaDBConnectionPool;
import by.epam.jwd.dao.interf.UserDao;
import by.epam.jwd.entity.User;

public class MariaDBUserDAO implements UserDao{
	
	private final MariaDBConnectionPool connectionPool = MariaDBConnectionPool.getConnectionPool();
	private static final String AUTHORIZATION = "SELECT * FROM users WHERE login=? AND";

	@Override
	public User authorization(String login, String password) throws DAOException {

		User user;

		try {
			ResultSet resultSet;
			Connection connection = connectionPool.takeConnection();
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE login=? AND password=?");
			ps.setString(1, login);
			ps.setString(2, password);

			resultSet = ps.executeQuery();

			//if (resultSet.next() && BCrypt.checkpw(password, resultSet.getString(PASSWORD))) {
				//userID = resultSet.getInt(ID);
		
			//return userID;
	
			
			connectionPool.returnConnection(connection, statement, resultSet);

	} catch (SQLException e) {
		throw new DAOException(e);
	}
	return User;
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
