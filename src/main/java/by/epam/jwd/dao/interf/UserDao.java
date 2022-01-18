package by.epam.jwd.dao.interf;

import by.epam.jwd.dao.DAOException;

public interface UserDao {
	
	String authorization(String login, String password) throws DAOException; //return role
	
	boolean registration (String login, String password) throws DAOException;
	
	boolean findLogin(String login) throws DAOException;

}
