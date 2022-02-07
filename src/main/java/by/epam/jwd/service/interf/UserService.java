package by.epam.jwd.service.interf;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.entity.User;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.ValidateException;

import java.util.List;
import java.util.Map;

public interface UserService {
	
	User authorization(String login, String password) throws ServiceException;

	void createUser(Map<String, String> param) throws ServiceException, ValidateException;

	List<User> readUsers() throws ServiceException;

	int getUserPageCount(String rowLimitStr) throws ServiceException;

	List<Integer> pagination(String pageStr, String rowLimitStr) throws ServiceException;

	List<User> readUsers(String pageStr, String rowLimitStr) throws ServiceException;

	User readUser(String idStr) throws ServiceException;

	User readUser(int id) throws ServiceException;

	List<User> findUsers(String param, String value) throws ServiceException;

	List<User> findUsers(Map<String, String> criteriaMap) throws ServiceException;

	void updateUser(Map<String, String> criteriaMap) throws ServiceException, ValidateException;

	User createUserEntity(Map<String, String> param) throws ServiceException;
}
