package by.epam.jwd.service;

import by.epam.jwd.service.interf.CarService;
import by.epam.jwd.service.interf.DriverService;
import by.epam.jwd.service.interf.OrderService;
import by.epam.jwd.service.interf.UserService;

public interface MDServiceFactory {

    DriverService getDriverService();

    CarService getCarService();

    UserService getUserService();

    OrderService getOrderService();
}
