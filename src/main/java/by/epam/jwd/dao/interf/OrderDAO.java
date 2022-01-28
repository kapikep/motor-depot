package by.epam.jwd.dao.interf;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.entity.Order;

import java.util.List;

public interface OrderDAO {

    List<Order> readAllOrders() throws DAOException;
}
