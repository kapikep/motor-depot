package by.epam.jwd.dao.implementation;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.dao.MotorDepotDAOFactory;
import by.epam.jwd.dao.connection_pool.MariaDBConnectionPool;
import by.epam.jwd.dao.interf.UserDao;
import org.junit.Test;

public class MariaDbUserDAOTest {

    @Test
    public void authorization() throws DAOException {

        try {
            MariaDBConnectionPool.initPool("testDb");
            MotorDepotDAOFactory motorDepotDAO = MotorDepotDAOFactory.getMotorDepotDAOFactory();
            UserDao userDao = motorDepotDAO.getUserDao();
            MariaDBConnectionPool.closeConnectionQueue();
        } catch (DAOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        MariaDBConnectionPool.closeConnectionQueue();
    }
}