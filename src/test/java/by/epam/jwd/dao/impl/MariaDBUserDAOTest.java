package by.epam.jwd.dao.impl;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.dao.interf.CarDAO;
import by.epam.jwd.dao.interf.MotorDepotDAO;
import by.epam.jwd.dao.interf.UserDao;
import org.junit.Test;

import static org.junit.Assert.*;

public class MariaDBUserDAOTest {

    @Test
    public void authorization() {
        MotorDepotDAO motorDepotDAO = MariaDBMotorDepotDAO.getMySqlMotorDeportDao();
        UserDao userDao = motorDepotDAO.getUserDao();

        try {
            System.out.println(userDao.authorization("Boris", "driver"));
        } catch (DAOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}