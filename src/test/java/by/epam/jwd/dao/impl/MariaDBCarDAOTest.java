package by.epam.jwd.dao.impl;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.dao.connection_pool.MariaDBConnectionPool;
import by.epam.jwd.dao.interf.CarDAO;
import by.epam.jwd.dao.interf.MotorDepotDAO;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.*;

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