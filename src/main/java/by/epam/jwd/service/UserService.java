package by.epam.jwd.service;

import by.epam.jwd.entity.User;

public interface UserService {
	
	User authorization(String login, String password) throws ServiceException; //return role
	
	boolean registration (String login, String password) throws ServiceException;
	
	boolean findLogin(String login) throws ServiceException;

}
