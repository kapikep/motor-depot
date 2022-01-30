package by.epam.jwd.service.interf;

import by.epam.jwd.entity.Car;
import by.epam.jwd.entity.Order;
import by.epam.jwd.service.ServiceException;

import java.util.List;
import java.util.Map;

public interface OrderService {
    void createOrder(Order order) throws ServiceException;

    void createOrder(Map<String, String> param) throws ServiceException;

    void createNotApproveOrder(String fullName, String phoneNumber, String criteria) throws ServiceException;

    List<Order> findOrders(String param, String value) throws ServiceException;

    List<Order> findOrders(Map<String, String> criteriaMap) throws ServiceException;

    Order readOrder(String id) throws ServiceException;

    List<Order> readOrders() throws ServiceException;

    int getOrderSize() throws ServiceException;

    List<Order> readOrders(String whereParam, String whereValue) throws ServiceException;

    List<Order> readOrders(int page, int limit) throws ServiceException;

    List<Order> readOrders(String page, String limit, String orderBy) throws ServiceException;

    void updateOrder(Car car) throws ServiceException;

    void deleteOrder(int id) throws ServiceException;

    List<Integer> pagination(String pageStr, String rowLimitStr) throws ServiceException;

    int getOrderPageCount(String rowLimitStr) throws ServiceException;

    List<Order> readOrders(String page, String limit, String whereParam, String whereValue) throws ServiceException;

    Order createOrderEntity(Map<String, String> param) throws ServiceException;
}
