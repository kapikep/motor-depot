package by.epam.jwd.dao.interf;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.entity.Order;

import java.util.List;
import java.util.Map;

public interface OrderDAO {

    void createOrder(Order order) throws DAOException;

    void createNotApproveOrder(Order order) throws DAOException;

    List<Order> findOrders(Map<String, String> criteriaMap) throws DAOException;

    List<Order> findOrders(String whereParam, String whereValue) throws DAOException;

    Order readOrder(int id) throws DAOException;

    List<Order> readOrders() throws DAOException;

    List<Order> readOrders(int page, int limit) throws DAOException;

    List<Order> findOrders(int page, int limit, String whereParam, String whereValue) throws DAOException;

    List<Order> readOrders(int page, int limit, String orderBy) throws DAOException;

    int getOrderSize() throws DAOException;

    void updateOrder(Order order) throws DAOException;

    void blockOrder(Order order) throws DAOException;

    void deleteOrder(int id) throws DAOException;
}
