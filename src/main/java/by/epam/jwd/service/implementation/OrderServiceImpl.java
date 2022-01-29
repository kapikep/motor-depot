package by.epam.jwd.service.implementation;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.dao.MotorDepotDAOFactory;
import by.epam.jwd.dao.interf.OrderDAO;
import by.epam.jwd.entity.Car;
import by.epam.jwd.entity.Order;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.ServiceUtil;
import by.epam.jwd.service.interf.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private final OrderDAO ORDER_DAO = MotorDepotDAOFactory.getMotorDepotDAOFactory().getOrderDao();

    @Override
    public void createOrder(Order order) throws ServiceException {
        try {
            ORDER_DAO.createOrder(order);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void createNotApproveOrder(String fullName, String phoneNumber, String criteria) throws ServiceException {
        try {
            ORDER_DAO.createNotApproveOrder(fullName, phoneNumber, criteria, new Date());
        } catch (DAOException e) {
            e.printStackTrace();
        }
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
    public Order readOrder(String idStr) throws ServiceException {
        Order order = null;
        int id = ServiceUtil.parseInt(idStr);

        try{
            order = ORDER_DAO.readOrder(id);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public int getOrderSize() throws ServiceException {
        return 0;
    }

    @Override
    public List<Order> readOrders(String whereParam, String whereValue) throws ServiceException {
        List<Order> orders;

        try {
            orders = ORDER_DAO.readOrders(whereParam, whereValue);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return orders;
    }

    @Override
    public List<Order> readOrders(int page, int limit) throws ServiceException {
        return null;
    }

    @Override
    public List<Order> readOrders(String pageStr, String rowLimitStr, String orderBy) throws ServiceException {
        List<Order> orders;
        int page = ServiceUtil.parseInt(pageStr);
        int rowLimit = ServiceUtil.parseInt(rowLimitStr, 10);

        try {
            orders = ORDER_DAO.readOrders(page, rowLimit);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return orders;
    }

    @Override
    public void updateOrder(Car car) throws ServiceException {

    }

    @Override
    public void deleteOrder(int id) throws ServiceException {

    }

    @Override
    public List<Order> readOrders() throws ServiceException {
        List<Order> orders;
        try {
            orders = ORDER_DAO.readOrders();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return orders;
    }

    @Override
    public List<Integer> pagination(String pageStr, String rowLimitStr) throws ServiceException {
        int rowLimit = ServiceUtil.parseInt(rowLimitStr, 10);
        int page = ServiceUtil.parseInt(pageStr);
        List<Integer> res;
        int pageCount = getOrderPageCount(rowLimitStr);
        res = ServiceUtil.pagination(pageCount, rowLimit, page);
        return res;
    }

    @Override
    public int getOrderPageCount(String rowLimitStr) throws ServiceException{
        int size;
        int rowLimit = ServiceUtil.parseInt(rowLimitStr, 10);

        try{
            size = ORDER_DAO.getOrderSize();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        size = (int) Math.ceil((double) size/rowLimit);
        return size;
    }

    @Override
    public List<Order> readOrders(String pageStr, String rowLimitStr, String whereParam, String whereValue) throws ServiceException {
        List<Order> orders;
        int page = ServiceUtil.parseInt(pageStr);
        int rowLimit = ServiceUtil.parseInt(rowLimitStr, 10);

        try {
            orders = ORDER_DAO.readOrders(page, rowLimit, whereParam, whereValue);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return orders;
    }
}
