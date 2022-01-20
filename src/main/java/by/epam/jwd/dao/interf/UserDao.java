package by.epam.jwd.dao.interf;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.entity.User;

public interface UserDao {
	
	User authorization(String login, String password) throws DAOException; //return role

	boolean createUser (User user) throws DAOException;

	User findUser(String login) throws DAOException;

	void updateUser(User user) throws DAOException;

}
