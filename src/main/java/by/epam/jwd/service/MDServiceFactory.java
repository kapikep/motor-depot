package by.epam.jwd.service;

import by.epam.jwd.service.implementation.CarServiceImpl;
import by.epam.jwd.service.implementation.DriverServiceImpl;
import by.epam.jwd.service.implementation.OrderServiceImpl;
import by.epam.jwd.service.implementation.UserServiceImpl;
import by.epam.jwd.service.interf.CarService;
import by.epam.jwd.service.interf.DriverService;
import by.epam.jwd.service.interf.OrderService;
import by.epam.jwd.service.interf.UserService;

public class MDServiceFactory {

    private static final MDServiceFactory INSTANCE = new MDServiceFactory();
    private static final CarService CAR_SERVICE = new CarServiceImpl();
    private static final UserService USER_SERVICE = new UserServiceImpl();
    private static final DriverService DRIVER_SERVICE = new DriverServiceImpl();
    private static final OrderService ORDER_SERVICE = new OrderServiceImpl();

    private MDServiceFactory(){}

    public static MDServiceFactory getMDService(){
        return INSTANCE;
    }

    public DriverService getDriverService(){
        return DRIVER_SERVICE;
    }

    public CarService getCarService() {
        return CAR_SERVICE;
    }

    public UserService getUserService() {
        return USER_SERVICE;
    }

    public OrderService getOrderService() {
        return ORDER_SERVICE;
    }
}
