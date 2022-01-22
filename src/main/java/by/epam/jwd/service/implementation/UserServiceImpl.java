package by.epam.jwd.service.implementation;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.dao.implementation.MariaDBMotorDepotDAO;
import by.epam.jwd.dao.interf.MotorDepotDAO;
import by.epam.jwd.dao.interf.UserDao;
import by.epam.jwd.entity.User;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.interf.UserService;
import by.epam.jwd.service.validator.UserValidator;

public class UserServiceImpl implements UserService {
	
	private final MotorDepotDAO motorDepotDAO = MariaDBMotorDepotDAO.getMySqlMotorDeportDao();
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
	public boolean registration(String login, String password) throws ServiceException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findLogin(String login) throws ServiceException {
		// TODO Auto-generated method stub
		return false;
	}

}
