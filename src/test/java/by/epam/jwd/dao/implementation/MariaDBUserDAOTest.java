package by.epam.jwd.dao.implementation;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.dao.connection_pool.MariaDBConnectionPool;
import by.epam.jwd.dao.interf.MotorDepotDAO;
import by.epam.jwd.dao.interf.UserDao;
import org.junit.Test;

public class MariaDBUserDAOTest {

    @Test
    public void authorization() throws DAOException {

        try {
            MariaDBConnectionPool.initPool();
            MotorDepotDAO motorDepotDAO = MariaDBMotorDepotDAO.getMySqlMotorDeportDao();
            UserDao userDao = motorDepotDAO.getUserDao();
            MariaDBConnectionPool.closeConnectionQueue();
        } catch (DAOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        MariaDBConnectionPool.closeConnectionQueue();
    }
}