package by.epam.jwd.dao.implementation;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.dao.interf.CarDAO;
import by.epam.jwd.dao.interf.MotorDepotDAO;
import org.junit.Test;

import java.sql.SQLException;

public class MariaDBCarDAOTest {

    @Test
    public void readAllCar() throws DAOException, SQLException {
        MotorDepotDAO motorDepotDAO = MariaDBMotorDepotDAO.getMySqlMotorDeportDao();
        CarDAO carDAO = motorDepotDAO.getCarDao();

        try {
            System.out.println(carDAO.readAllCar());
        } catch (DAOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}