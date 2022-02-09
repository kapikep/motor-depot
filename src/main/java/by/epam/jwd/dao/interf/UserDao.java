package by.epam.jwd.dao.interf;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.entity.User;

import java.util.List;
import java.util.Map;

public interface UserDao {

	List<User> readUsers(int page, int limit) throws DAOException;

	List<User> findUsers(int page, int limit, String whereParam, String whereValue) throws DAOException;

	List<User> readUsers(int page, int limit, String orderBy) throws DAOException;

	List<User> readUsers() throws DAOException;

	List<User> findUsers(String whereParam, String whereValue) throws DAOException;

	List<User> findUsers(Map<String, String> criteriaMap) throws DAOException;

	int getUsersSize() throws DAOException;

	User authorization(String login, String password) throws DAOException; //return role

	void createUser (User user) throws DAOException;

	User readUser(int id) throws DAOException;

	void updateUser(User user) throws DAOException;

	void deleteUser(int id) throws DAOException;
}
