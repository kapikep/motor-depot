package by.epam.jwd.dao.impl;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.dao.interf.CarDAO;
import by.epam.jwd.dao.interf.UserDao;

public class MariaDBUserDAO implements UserDao{

	@Override
	public String authorization(String login, String password) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean registration(String login, String password) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findLogin(String login) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

}
