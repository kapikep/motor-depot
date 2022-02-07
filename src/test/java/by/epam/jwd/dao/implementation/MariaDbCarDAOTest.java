package by.epam.jwd.dao.implementation;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.dao.MotorDepotDAOFactory;
import by.epam.jwd.dao.connection_pool.MariaDBConnectionPool;
import by.epam.jwd.dao.interf.CarDAO;
import by.epam.jwd.entity.*;
import org.junit.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MariaDbCarDAOTest {

    CarDAO carDAO = MotorDepotDAOFactory.getMotorDepotDAOFactory().getCarDao();
    CarModel carModel;
    CarModel carModelUpd;
    Car car;
    Car carUpd;


    @BeforeClass
    public static void beforeClass() throws Exception {
        MariaDBConnectionPool.initPool("db");
    }

    @Before
    public void setUp() throws Exception {
        int licencePlate = 1234;
        licencePlate++;

        car = new Car(1, licencePlate + " AC-7", "black", "nan", 6666, "utilize", 1);
        carUpd = new Car(10, "new", "red", "nan", 9999, "", 3);
        carModel = new CarModel(4, "Beatle", "car", 700, 4, "2x2");
        carModel = new CarModel(4, "Audi", "car", 800, 4, "2x2");
    }

    @Test
    public void getVarSizeTest() {
        int sizeOne = 0;
        int sizeTwo = 0;
        try {
            sizeOne = carDAO.getCarSize();
            sizeTwo = carDAO.getCarSize();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(sizeOne > 0);
        Assert.assertEquals(sizeOne, sizeTwo);
    }

    @Test
    public void findCarsTest() {
        Map<String, String> map = new HashMap<>();
        map.put("status", "active");
        map.put("odometr", ">9999");

        try {
            carDAO.findCars("licence_plate", "1213 AC-7");
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
            carList = carDAO.readCars(2, offset);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(offset, carList.size());
    }

    @Test
    public void readCarTypesTest() {
        try{
            carDAO.readCarTypes();
        } catch (DAOException e) {
            e.printStackTrace();
        }
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

//    @Test
//    public void updateCarTest() {
//        Boolean result = false;
//        try {
//            result = carDAO.updateCar(carUpd);
//        } catch (DAOException e) {
//            e.printStackTrace();
//        }
//        Assert.assertTrue(result);
//    }

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
//            result = carDAO.createModel(carModel);
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
            carList = carDAO.readCars();
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