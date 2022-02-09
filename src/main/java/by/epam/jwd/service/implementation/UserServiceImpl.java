package by.epam.jwd.service.implementation;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.dao.MotorDepotDAOFactory;
import by.epam.jwd.dao.interf.UserDao;
import by.epam.jwd.entity.*;
import by.epam.jwd.entity.User;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.ServiceUtil;
import by.epam.jwd.service.ValidateException;
import by.epam.jwd.service.interf.UserService;
import by.epam.jwd.service.validator.UserValidator;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
	
    private final UserDao USER_DAO = MotorDepotDAOFactory.getMotorDepotDAOFactory().getUserDao();

	@Override
	public User authorization(String login, String password) throws ServiceException {
		User user;

			try {
				user = USER_DAO.authorization(login, password);
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		return user;
	}

	@Override
	public void createUser(Map<String, String> param) throws ServiceException, ValidateException {
		User user;

		try {
			UserValidator.userFieldValueValidate(param);
			user = createUserEntity(param);
			USER_DAO.createUser(user);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<User> readUsers() throws ServiceException {
		List<User> Users;
		try {
			Users = USER_DAO.readUsers();
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
			size = USER_DAO.getUsersSize();
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
			Users = USER_DAO.readUsers(page, rowLimit);
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
			User = USER_DAO.readUser(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return User;
	}

	@Override
	public User readUser(int id) throws ServiceException {
		User User = null;
		try {
			User = USER_DAO.readUser(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return User;
	}

	@Override
	public List<User> findUsers(String param, String value) throws ServiceException {
		List<User> Users;
		try {
			Users = USER_DAO.findUsers(param, value);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return Users;
	}

	@Override
	public List<User> findUsers(Map<String, String> criteriaMap) throws ServiceException {

		List<User> Users = null;
		try {
			Users = USER_DAO.findUsers(criteriaMap);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return Users;
	}

	@Override
	public void updateUser(Map<String, String> param) throws ServiceException, ValidateException {
		User user;

		try {
			UserValidator.userFieldValueValidate(param);
			user = createUserEntity(param);
			USER_DAO.updateUser(user);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public User createUserEntity(Map<String, String> param) throws ServiceException {
		User User = new User();

		String id = param.get("id");
		if (id != null && !("".equals(id))) {
			User.setId(Integer.parseInt(id));
		}
		User.setName(param.get("name"));
		User.setSurname(param.get("surname"));
		User.setLogin(param.get("login"));
		User.setPassword(param.get("password"));
		User.setPhoneNumber(param.get("phoneNumber"));
		User.setStatus(Status.valueOf(param.get("status")));
		User.seteMail(param.get("eMail"));
		User.setAdditionalInfo(param.get("additionalInfo"));
		User.setRole(Role.valueOf(param.get("role")));

		return User;
	}

	@Override
	public void deleteUser(int id) throws ServiceException {
		try {
			USER_DAO.deleteUser(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}

