package by.epam.jwd.dao.implementation;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.dao.connection_pool.MariaDBConnectionPool;
import by.epam.jwd.dao.interf.CarDAO;
import by.epam.jwd.dao.interf.MotorDepotDAO;
import by.epam.jwd.entity.*;
import org.junit.*;

import java.util.List;
import java.util.Map;

public class MariaDbCarDAOTest {

    MotorDepotDAO motorDepotDAO = MariaDbMotorDepotDAO.getMySqlMotorDeportDao();
    CarDAO carDAO = motorDepotDAO.getCarDao();
    CarModel carModel;
    CarModel carModelUpd;
    Car car;
    Car carUpd;

    @BeforeClass
    public static void beforeClass() throws Exception {
        MariaDBConnectionPool.initPool("testDb");
    }

    @Before
    public void setUp() throws Exception {
        car = new Car(1, "1213 AC-7", "black", "nan", 6666, "utilize", 1);
        carUpd = new Car(10, "new", "red", "nan", 9999, "", 3);
        carModel = new CarModel(4, "Beatle", "car", 700, 4, "2x2");
        carModel = new CarModel(4, "Audi", "car", 800, 4, "2x2");
    }

    @Test
    public void findCarsTest() {
        Map<String, String> map = null;
        try {
            carDAO.findCars(map);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(1, car.getId());
    }

    @Test
    public void readCarsWithOffsetTest(){
        int offset = 5;
        List<Car> carList = null;
        try {
            carList = carDAO.readCarsWithOffset(2, offset);
            System.out.println(carList);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(offset, carList.size());
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
    public void updateCarTest() {
        Boolean result = false;
        try {
            result = carDAO.updateCar(carUpd);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(result);
    }

    @Test
    public void readCarTest() {
        Car car = null;
        try {
            car = carDAO.readCar(1);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(1, car.getId());
    }

//    @Test
//    public void createMadelTest() {
//        Boolean result = false;
//        try {
//            result = carDAO.createMadel(carModel);
//        } catch (DAOException e) {
//            e.printStackTrace();
//        }
//        Assert.assertTrue(result);
//    }

//    @Test
//    public void test() {
//        MariaDBConnectionPool CONNECTION_POOL = MariaDBConnectionPool.getConnectionPool();
//        try {
//            Connection connection = CONNECTION_POOL.takeConnection();
//            Statement statement = connection.createStatement();
//
//            CONNECTION_POOL.returnConnection(connection, statement);
//        } catch (SQLException | DAOException e) {
//            e.printStackTrace();
//        }
//    }

    @Test
    public void readAllCar(){
        List<Car> carList = null;

        try {
            carList = carDAO.readAllCar();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(carList.get(0));
    }

    @AfterClass
    public static void afterClass() throws Exception {
        MariaDBConnectionPool.closeConnectionQueue();
    }
}