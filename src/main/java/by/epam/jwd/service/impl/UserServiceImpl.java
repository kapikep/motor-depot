package by.epam.jwd.service.impl;

import by.epam.jwd.dao.impl.MariaDBMotorDepotDAO;
import by.epam.jwd.dao.interf.CarDAO;
import by.epam.jwd.dao.interf.MotorDepotDAO;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.UserService;

public class UserServiceImpl implements UserService {
	
	MotorDepotDAO motorDepotDAO = MariaDBMotorDepotDAO.getMySqlMotorDeportDao();
    CarDAO carDAO = motorDepotDAO.getCarDao();

	@Override
	public String authorization(String login, String password) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
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
