package by.epam.jwd.service.implementation;

import by.epam.jwd.service.MDServiceFactory;
import by.epam.jwd.service.interf.CarService;
import by.epam.jwd.service.interf.DriverService;
import by.epam.jwd.service.interf.OrderService;
import by.epam.jwd.service.interf.UserService;

public class MDServiceFactoryImpl implements MDServiceFactory{

    private static final MDServiceFactory INSTANCE = new MDServiceFactoryImpl();
    private static final CarService CAR_SERVICE = new CarServiceImpl();
    private static final OrderService ORDER_SERVICE = new OrderServiceImpl();
    private static final UserService USER_SERVICE = new UserServiceImpl();
    private static final DriverService DRIVER_SERVICE = new DriverServiceImpl();

    private MDServiceFactoryImpl(){}

    public static MDServiceFactory getMDService(){
        return INSTANCE;
    }

    @Override
    public DriverService getDriverService(){
        return DRIVER_SERVICE;
    }

    @Override
    public CarService getCarService() {
        return CAR_SERVICE;
    }

    @Override
    public UserService getUserService() {
        return USER_SERVICE;
    }

    @Override
    public OrderService getOrderService() {
        return ORDER_SERVICE;
    }
}
