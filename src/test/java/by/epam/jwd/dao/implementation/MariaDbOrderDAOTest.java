package by.epam.jwd.dao.implementation;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.dao.MotorDepotDAOFactory;
import by.epam.jwd.dao.connection_pool.MariaDBConnectionPool;
import by.epam.jwd.dao.interf.OrderDAO;
import by.epam.jwd.entity.Order;
import by.epam.jwd.entity.Status;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

public class MariaDbOrderDAOTest {
    OrderDAO orderDAO = MotorDepotDAOFactory.getMotorDepotDAOFactory().getOrderDao();
    Map<String, String> map = new HashMap<>();
    Order order;

    @BeforeClass
    public static void beforeClass() throws Exception {
        MariaDBConnectionPool.initPool("db");
    }

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void readAllOrdersTest() {
        List<Order> cars = new ArrayList<>();
        try {
            cars = orderDAO.readOrders();
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

//    @Test
//    public void name() {
//        Order order = new Order();
//        order.setCriteria("Микроавтобус на завтра 10 часов");
//        order.setRequestDate(new Date());
//        order.setClientId(1);
//        try {
//            orderDAO.createNotApproveOrder(order);
//        } catch (DAOException e) {
//            e.printStackTrace();
//        }
//    }

//    @Test
//    public void createOrderTest() {
//        order = new Order("Самосвал, вывоз песка", new Date(), "Сенница",
//                "Гатово", new Date(), new Date(), Status.APPROVE.toString(), 50, 20, "not paid",
//                2, "Владимир Иванович", "+37589898989", 1, 2, 1);
//        try {
//            orderDAO.createOrder(order);
//        } catch (DAOException e) {
//            e.printStackTrace();
//        }
//    }


    @Test
    public void findOrdersTest() {
        map.put("start_date>", "2020-02-01 23:11:05.064");
        map.put("end_date<", "2022-04-22 23:11:00.0");
        try {
            orderDAO.findOrders(map);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateOrderTest() {
        order = new Order("Погрузчик, 22 сентября", new Date(), "Сенница",
                "Гатово", new Date(), new Date(), Status.APPROVE.toString(), 50, 20, "not paid",
                2, "Владимир Иванович", "+37589898989", 1, 2, 1);
        order.setId(32);
        try {
            orderDAO.updateOrder(order);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void afterClass() throws Exception {
        MariaDBConnectionPool.closeConnectionQueue();
    }
}