package by.epam.jwd.service.implementation;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.dao.MotorDepotDAOFactory;
import by.epam.jwd.dao.interf.DriverDAO;
import by.epam.jwd.dao.interf.OrderDAO;
import by.epam.jwd.entity.Driver;
import by.epam.jwd.entity.Order;
import by.epam.jwd.entity.Status;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.ServiceUtil;
import by.epam.jwd.service.interf.OrderService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private final OrderDAO ORDER_DAO = MotorDepotDAOFactory.getMotorDepotDAOFactory().getOrderDao();
    private final DriverDAO DRIVER_DAO = MotorDepotDAOFactory.getMotorDepotDAOFactory().getDriverDao();

    @Override
    public void createOrder(Map<String, String> param) throws ServiceException {
        List<Driver> drivers;
        String clientId = param.get("clientId");
        if (clientId == null || "".equals(clientId)) {
            param.put("clientId", "1");
        }
        try {
            drivers = DRIVER_DAO.findDrivers("attached_car_id", param.get("carId"));
            if (drivers.isEmpty()) {
                param.put("driverId", "1");
            } else {
                param.put("driverId", Integer.toString(drivers.get(0).getUserId()));
            }
            Order order = createOrderEntity(param);
            String s = param.get("editId");
            if (s != null && !("".equals(s))) {
                order.setId(Integer.parseInt(s));
            }
            ORDER_DAO.updateOrder(order);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateOrder(Map<String, String> param) throws ServiceException {
        List<Driver> drivers;
        String clientId = param.get("clientId");
        if (clientId == null || "".equals(clientId)) {
            param.put("clientId", "1");
        }
        try {
            drivers = DRIVER_DAO.findDrivers("attached_car_id", param.get("carId"));
            if (drivers.isEmpty()) {
                param.put("driverId", "1");
            } else {
                param.put("driverId", Integer.toString(drivers.get(0).getUserId()));
            }
            Order order = createOrderEntity(param);
            ORDER_DAO.updateOrder(order);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> readOrders(Map<String, String> param) throws ServiceException {
        List<Order> orders = null;
        try {
            orders = ORDER_DAO.findOrders(param);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return orders;
    }

    @Override
    public void updateOrder(Order order) throws ServiceException {

        try {
            ORDER_DAO.createOrder(order);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void createNotApproveOrder(String fullName, String phoneNumber, String criteria, int userId) throws ServiceException {
        Order order = new Order();
        try {
            order.setClientFullName(fullName);
            order.setClientPhone(phoneNumber);
            order.setCriteria(criteria);
            order.setOrderStatus(Status.NOT_APPROVE.toString());
            order.setRequestDate(new Date());
            order.setClientId(userId);
            ORDER_DAO.createNotApproveOrder(order);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> findOrders(Map<String, String> criteriaMap) throws ServiceException {
        List<Order> orders;

        try {
            orders = ORDER_DAO.findOrders(criteriaMap);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return orders;
    }

    @Override
    public Order readOrder(String idStr) throws ServiceException {
        Order order = null;
        int id = ServiceUtil.parseInt(idStr);

        try {
            order = ORDER_DAO.readOrder(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return order;
    }

    @Override
    public int getOrderSize() throws ServiceException {
        int size = 0;
        try {
            size = ORDER_DAO.getOrderSize();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return size;
    }

    @Override
    public List<Order> findOrders(String whereParam, String whereValue) throws ServiceException {
        List<Order> orders;

        try {
            orders = ORDER_DAO.findOrders(whereParam, whereValue);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return orders;
    }

    @Override
    public List<Order> readOrders(String pageStr, String rowLimitStr) throws ServiceException {
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
    public List<Order> readOrders(String pageStr, String rowLimitStr, String orderBy) throws ServiceException {
        List<Order> orders;
        int page = ServiceUtil.parseInt(pageStr);
        int rowLimit = ServiceUtil.parseInt(rowLimitStr, 10);

        try {
            orders = ORDER_DAO.readOrders(page, rowLimit, orderBy);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return orders;
    }

    @Override
    public void deleteOrder(int id) throws ServiceException {
        try {
            ORDER_DAO.deleteOrder(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
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
        res = ServiceUtil.getPagesNumber(pageCount, rowLimit, page);
        return res;
    }

    @Override
    public int getOrderPageCount(String rowLimitStr) throws ServiceException {
        int size;
        int rowLimit = ServiceUtil.parseInt(rowLimitStr, 10);

        try {
            size = ORDER_DAO.getOrderSize();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        size = (int) Math.ceil((double) size / rowLimit);
        return size;
    }

    @Override
    public List<Order> readOrders(String pageStr, String rowLimitStr, String whereParam, String whereValue) throws ServiceException {
        List<Order> orders;
        int page = ServiceUtil.parseInt(pageStr);
        int rowLimit = ServiceUtil.parseInt(rowLimitStr, 10);

        try {
            orders = ORDER_DAO.findOrders(page, rowLimit, whereParam, whereValue);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return orders;
    }

    @Override
    public Order createOrderEntity(Map<String, String> param) throws ServiceException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Order order = new Order();

        String id = param.get("id");
        if (id != null && !("".equals(id))) {
            order.setId(Integer.parseInt(id));
        }
        order.setCriteria(param.get("criteria"));
        try {
            String requestDateStr = param.get("requestDate");
            if (requestDateStr != null && !("".equals(requestDateStr))) {
                order.setRequestDate(sdf.parse(requestDateStr));
            } else {
                order.setRequestDate(new Date());
            }
            String startDateStr = param.get("startDate");
            if (startDateStr != null && !("".equals(startDateStr))) {
                order.setStartDate(sdf.parse(startDateStr));
            }
            String endDateStr = param.get("endDate");
            if (endDateStr != null && !("".equals(endDateStr))) {
                order.setEndDate(sdf.parse(endDateStr));
            }
        } catch (ParseException e) {
            throw new ServiceException(e);
        }
        order.setDepartPlace(param.get("departPlace"));
        order.setArrivalPlace(param.get("arrivalPlace"));
        order.setOrderStatus(param.get("status"));
        String distanceStr = param.get("distance");
        if (distanceStr != null && !("".equals(distanceStr))) {
            order.setDistance(Integer.parseInt(distanceStr));
        }
        String totalAmount = param.get("totalAmount");
        if (totalAmount != null && !("".equals(totalAmount))) {
            order.setTotalAmount(Integer.parseInt(totalAmount));
        }
        order.setPaymentStatus(param.get("paymentStatus"));
        String clientId = param.get("clientId");
        if (clientId != null && !("".equals(clientId))) {
            order.setClientId(Integer.parseInt(clientId));
        }
        String carId = param.get("carId");
        if (carId != null && !("".equals(carId))) {
            order.setCarId(Integer.parseInt(carId));
        }
        String driverId = param.get("driverId");
        if (driverId != null && !("".equals(driverId))) {
            order.setDriverId(Integer.parseInt(driverId));
        }
        String adminId = param.get("adminId");
        if (adminId != null && !("".equals(adminId))) {
            order.setAdminId(Integer.parseInt(adminId));
        }
        order.setClientFullName(param.get("clientFullName"));
        order.setClientPhone(param.get("clientPhone"));
        order.setCarLicensePlate(param.get("carLicensePlate"));
        order.setDriverName(param.get("driverName"));
        order.setDriverSurname(param.get("driverSurname"));
        order.setAdminName(param.get("adminName"));
        order.setAdminSurname(param.get("adminSurname"));

        return order;
    }
}
