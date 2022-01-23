package by.epam.jwd.dao.implementation;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.dao.connection_pool.MariaDBConnectionPool;
import by.epam.jwd.dao.interf.CarDAO;
import by.epam.jwd.dao.interf.MotorDepotDAO;
import by.epam.jwd.entity.*;
import org.junit.*;

import java.sql.*;
import java.util.List;

public class MariaDBCarDAOTest {

    MotorDepotDAO motorDepotDAO = MariaDBMotorDepotDAO.getMySqlMotorDeportDao();
    CarDAO carDAO = motorDepotDAO.getCarDao();
    CarModel carModel;
    Car car;

    @BeforeClass
    public static void beforeClass() throws Exception {
        MariaDBConnectionPool.initPool();
    }

    @Before
    public void setUp() throws Exception {
        car = new Car(1, "1213 AC-7", "black", "nan", 6666, "utilize", 1);
        carModel = new CarModel(4, "Beatle", "car", 700, 4, "2x2");
    }

    @Test
    public void createCarTest() {
        Boolean result = false;
        try {
            result = carDAO.createCar(car);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(result);
    }

    @Test
    public void createMadelTest() {
        Boolean result = false;
        try {
            result = carDAO.createMadel(carModel);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(result);
    }

    @Test
    public void test() {
        MariaDBConnectionPool CONNECTION_POOL = MariaDBConnectionPool.getConnectionPool();
        try {
            Connection connection = CONNECTION_POOL.takeConnection();
            Statement statement = connection.createStatement();

            CONNECTION_POOL.returnConnection(connection, statement);
        } catch (SQLException | DAOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readAllCar(){
        List<Car> carList = null;
        
        try {
            carList = carDAO.readAllCar();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(carList.get(1));
    }

    @AfterClass
    public static void afterClass() throws Exception {
        MariaDBConnectionPool.closeConnectionQueue();
    }
}