package by.epam.jwd.service.implementation;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.dao.MotorDepotDAOFactory;
import by.epam.jwd.dao.interf.OrderDAO;
import by.epam.jwd.entity.Order;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.interf.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final OrderDAO ORDER_DAO = MotorDepotDAOFactory.getMotorDepotDAOFactory().getOrderDao();

    @Override
    public List<Order> readAllOrders() throws ServiceException {
        List<Order> orders;

        try {
            orders = ORDER_DAO.readAllOrders();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return orders;
    }

}
