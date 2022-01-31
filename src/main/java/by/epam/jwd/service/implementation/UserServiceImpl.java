package by.epam.jwd.service.implementation;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.dao.MotorDepotDAOFactory;
import by.epam.jwd.dao.interf.UserDao;
import by.epam.jwd.entity.User;
import by.epam.jwd.entity.Order;
import by.epam.jwd.service.MDServiceFactory;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.ServiceUtil;
import by.epam.jwd.service.interf.UserService;
import by.epam.jwd.service.validator.UserValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
	
	private final MotorDepotDAOFactory motorDepotDAO = MotorDepotDAOFactory.getMotorDepotDAOFactory();
    private final UserDao userDao = motorDepotDAO.getUserDao();

	@Override
	public User authorization(String login, String password) throws ServiceException{
		User user = null;

		if(UserValidator.authorizationValidate(login, password)) {
			try {
				user = userDao.authorization(login, password);
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		}
		return user;
	}

	@Override
	public void createUser(Map<String, String> param) throws ServiceException {
		User user = null;
		try {
			//TODO validate
			userDao.createUser(user);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<User> readUsers() throws ServiceException {
		List<User> Users;
		try {
			Users = userDao.readUsers();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return Users;
	}

	@Override
	public int getUserPageCount(String rowLimitStr) throws ServiceException {
		int size;
		int rowLimit = ServiceUtil.parseInt(rowLimitStr, 10);

		try {
			size = userDao.getUsersSize();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		size = (int) Math.ceil((double) size / rowLimit);
		return size;
	}

	@Override
	public List<Integer> pagination(String pageStr, String rowLimitStr) throws ServiceException {
		int rowLimit = ServiceUtil.parseInt(rowLimitStr, 10);
		int page = ServiceUtil.parseInt(pageStr);
		List<Integer> res;
		int pageCount = getUserPageCount(rowLimitStr);
		res = ServiceUtil.getPagesNumber(pageCount, rowLimit, page);
		return res;
	}

	@Override
	public List<User> readUsers(String pageStr, String rowLimitStr) throws ServiceException {
		List<User> Users;
		int page = ServiceUtil.parseInt(pageStr);
		int rowLimit = ServiceUtil.parseInt(rowLimitStr, 10);

		try {
			Users = userDao.readUsers(page, rowLimit);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return Users;
	}

	@Override
	public User readUser(String idStr) throws ServiceException {
		User User = null;
		int id = ServiceUtil.parseInt(idStr);
		try {
			User = userDao.readUser(id);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return User;
	}

	@Override
	public List<User> findUsers(String param, String value) throws ServiceException {
		List<User> Users;
		try {
			Users = userDao.findUsers(param, value);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return Users;
	}

	@Override
	public List<User> findUsers(Map<String, String> criteriaMap) throws ServiceException {
		if("".equals(criteriaMap.get("load_capacity"))){
			criteriaMap.put("load_capacity", ">1");
		}

		if("".equals(criteriaMap.get("passenger_capacity"))){
			criteriaMap.put("passenger_capacity", ">1");
		}

		List<User> Users = null;
		try {
			Users = userDao.findUsers(criteriaMap);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return Users;
	}

	@Override
	public void updateUser(Map<String, String> criteriaMap) throws ServiceException {
		User user = null;
		try {
			//TODO validate
			userDao.updateUser(user);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
