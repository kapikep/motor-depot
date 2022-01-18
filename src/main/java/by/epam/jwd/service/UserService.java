package by.epam.jwd.service;

import by.epam.jwd.dao.DAOException;

public interface UserService {
	
	String authorization(String login, String password) throws ServiceException; //return role
	
	boolean registration (String login, String password) throws ServiceException;
	
	boolean findLogin(String login) throws ServiceException;

}
