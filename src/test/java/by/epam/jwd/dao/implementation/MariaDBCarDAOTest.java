package by.epam.jwd.dao.implementation;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.dao.connection_pool.MariaDBConnectionPool;
import by.epam.jwd.dao.interf.CarDAO;
import by.epam.jwd.dao.interf.MotorDepotDAO;
import org.junit.Test;

import java.sql.SQLException;

public class MariaDBCarDAOTest {

    @Test
    public void readAllCar() throws DAOException, SQLException {

        try {
            MariaDBConnectionPool.initPool();
            MotorDepotDAO motorDepotDAO = MariaDBMotorDepotDAO.getMySqlMotorDeportDao();
            CarDAO carDAO = motorDepotDAO.getCarDao();
            System.out.println(carDAO.readAllCar());
            MariaDBConnectionPool.closeConnectionQueue();
        } catch (DAOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}