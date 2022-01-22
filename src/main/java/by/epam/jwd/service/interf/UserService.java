package by.epam.jwd.service.interf;

import by.epam.jwd.entity.User;
import by.epam.jwd.service.ServiceException;

public interface UserService {
	
	User authorization(String login, String password) throws ServiceException; //return role
	
	boolean registration (String login, String password) throws ServiceException;
	
	boolean findLogin(String login) throws ServiceException;

}
