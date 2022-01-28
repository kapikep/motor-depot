package by.epam.jwd.dao.implementation;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.dao.MotorDepotDAOFactory;
import by.epam.jwd.dao.connection_pool.MariaDBConnectionPool;
import by.epam.jwd.dao.interf.OrderDAO;
import by.epam.jwd.entity.Order;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MariaDbOrderDAOTest {
    OrderDAO orderDAO = MotorDepotDAOFactory.getMotorDepotDAOFactory().getOrderDao();

    @BeforeClass
    public static void beforeClass() throws Exception {
        MariaDBConnectionPool.initPool("db");
    }

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void name() {
        List<Order> cars = new ArrayList<>();
        try {
            cars = orderDAO.readAllOrders();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        System.out.println(cars);
    }

    @AfterClass
    public static void afterClass() throws Exception {
        MariaDBConnectionPool.closeConnectionQueue();
    }
}