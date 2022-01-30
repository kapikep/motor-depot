package by.epam.jwd.dao;

import by.epam.jwd.dao.connection_pool.MariaDBConnectionPool;
import by.epam.jwd.dao.implementation.MariaDbCarDAO;
import by.epam.jwd.dao.implementation.MariaDbDriverDAO;
import by.epam.jwd.dao.implementation.MariaDbOrderDAO;
import by.epam.jwd.dao.implementation.MariaDbUserDAO;
import by.epam.jwd.dao.interf.CarDAO;
import by.epam.jwd.dao.interf.DriverDAO;
import by.epam.jwd.dao.interf.OrderDAO;
import by.epam.jwd.dao.interf.UserDao;

public class MotorDepotDAOFactory {

    private static final MotorDepotDAOFactory INSTANCE = new MotorDepotDAOFactory();
    private static final CarDAO CAR_DAO = new MariaDbCarDAO();
    private static final UserDao USER_DAO = new MariaDbUserDAO();
    private static final OrderDAO ORDER_DAO = new MariaDbOrderDAO();
    private static final DriverDAO DRIVER_DAO = new MariaDbDriverDAO();

    private MotorDepotDAOFactory(){}

    public static MotorDepotDAOFactory getMotorDepotDAOFactory(){
        return INSTANCE;
    }

    public CarDAO getCarDao() {
        return CAR_DAO;
    }

    public  UserDao getUserDao() {
        return USER_DAO;
    }

    public OrderDAO getOrderDao() {
        return ORDER_DAO;
    }

    public DriverDAO getDriverDao() {
        return DRIVER_DAO;
    }
}
