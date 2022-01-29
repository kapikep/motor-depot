package by.epam.jwd.dao.interf;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.entity.Car;
import by.epam.jwd.entity.Order;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderDAO {

    void createOrder(Order order) throws DAOException;

    void createNotApproveOrder(String fullName, String phoneNumber, String criteria, Date requestDate) throws DAOException;

    void createNotApproveOrder(Order order) throws DAOException;

    List<Order> findOrders(String param, String value) throws DAOException;

    List<Order> findOrders(Map<String, String> criteriaMap) throws DAOException;

    Order readOrder(int id) throws DAOException;

    List<Order> readOrders() throws DAOException;

    List<Order> readOrders(String whereParam, String whereValue) throws DAOException;

    int getOrderSize() throws DAOException;

    List<Order> readOrders(int page, int limit) throws DAOException;

    List<Order> readOrders(int page, int limit, String whereParam, String whereValue) throws DAOException;

    List<Order> readOrders(int page, int limit, String orderBy) throws DAOException;

    void updateOrder(Car car) throws DAOException;

    void deleteOrder(int id) throws DAOException;
}
