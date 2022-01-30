package by.epam.jwd.dao.implementation;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.dao.MotorDepotDAOFactory;
import by.epam.jwd.dao.connection_pool.MariaDBConnectionPool;
import by.epam.jwd.dao.interf.DriverDAO;
import by.epam.jwd.dao.interf.OrderDAO;
import by.epam.jwd.entity.Driver;
import by.epam.jwd.entity.Role;
import by.epam.jwd.entity.Status;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;
import java.util.List;


public class MariaDbDriverDAOTest {
    DriverDAO driverDAO = MotorDepotDAOFactory.getMotorDepotDAOFactory().getDriverDao();
    Driver driver = new Driver();
    List<Driver> drivers = null;
    Driver driverRes;
    int limit = 7;
    int page = 1;
    int tableSize = 3;

    @BeforeClass
    public static void beforeClass() throws Exception {
        MariaDBConnectionPool.initPool("db");
    }

//    @Test
//    public void createDriverTest() {
//        driver = new Driver("Yan", "Urevich", "Yan", "driver", "+375662225544", "", Status.ACTIVE,
//                "mymail@gmail.com", "", Role.DRIVER, "C, D, E", 5, new Date(), new Date(), 1);
//        try{
//            driverDAO.createDriver(driver);
//        } catch (DAOException e) {
//            e.printStackTrace();
//        }
//    }


    @Test
    public void readDriversTest() {

        try{
            drivers = driverDAO.readDrivers();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(tableSize, drivers.size());
    }

    @Test
    public void findDriverTest() {
        try{
            drivers = driverDAO.findDrivers("category", "C%");
        } catch (DAOException e) {
            e.printStackTrace();
        }
        System.out.println(drivers);
    }

    @Test
    public void getDriversSizeTest() {
        int size = 0;
        try{
            size = driverDAO.getDriversSize();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(tableSize, size);
    }

    @AfterClass
    public static void afterClass() throws Exception {
        MariaDBConnectionPool.closeConnectionQueue();
    }

}