package by.epam.jwd.service.implementation;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.dao.MotorDepotDAOFactory;
import by.epam.jwd.dao.interf.OrderDAO;
import by.epam.jwd.entity.Car;
import by.epam.jwd.entity.Order;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.interf.OrderService;

import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private final OrderDAO ORDER_DAO = MotorDepotDAOFactory.getMotorDepotDAOFactory().getOrderDao();

    @Override
    public void createOrder(Order order) throws ServiceException {

    }

    @Override
    public void createNotApproveOrder(Order order) throws ServiceException {

    }

    @Override
    public List<Order> findOrders(String param, String value) throws ServiceException {
        return null;
    }

    @Override
    public List<Order> findOrders(Map<String, String> criteriaMap) throws ServiceException {
        return null;
    }

    @Override
    public Order readOrder(int id) throws ServiceException {
        return null;
    }

    @Override
    public int getOrderSize() throws ServiceException {
        return 0;
    }

    @Override
    public List<Order> readOrdersWithOffset(int page, int limit) throws ServiceException {
        return null;
    }

    @Override
    public List<Order> readOrdersWithOffset(int page, int limit, String orderBy) throws ServiceException {
        return null;
    }

    @Override
    public void updateOrder(Car car) throws ServiceException {

    }

    @Override
    public void deleteOrder(int id) throws ServiceException {

    }

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
