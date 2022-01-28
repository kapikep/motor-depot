package by.epam.jwd.service.interf;

import by.epam.jwd.entity.Car;
import by.epam.jwd.entity.Order;
import by.epam.jwd.service.ServiceException;

import java.util.List;
import java.util.Map;

public interface OrderService {
    void createOrder(Order order) throws ServiceException;

    void createNotApproveOrder(Order order) throws ServiceException;

    List<Order> findOrders(String param, String value) throws ServiceException;

    List<Order> findOrders(Map<String, String> criteriaMap) throws ServiceException;

    Order readOrder(int id) throws ServiceException;

    List<Order> readAllOrders() throws ServiceException;

    int getOrderSize() throws ServiceException;

    List<Order> readOrdersWithOffset(int page, int limit) throws ServiceException;

    List<Order> readOrdersWithOffset(int page, int limit, String orderBy) throws ServiceException;

    void updateOrder(Car car) throws ServiceException;

    void deleteOrder(int id) throws ServiceException;
}
