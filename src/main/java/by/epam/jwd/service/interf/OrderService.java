package by.epam.jwd.service.interf;

import by.epam.jwd.entity.Order;
import by.epam.jwd.service.ServiceException;

import java.util.List;

public interface OrderService {
    List<Order> readAllOrders() throws ServiceException;
}
