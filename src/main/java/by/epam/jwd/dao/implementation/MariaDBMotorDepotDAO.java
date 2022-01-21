package by.epam.jwd.dao.implementation;

import by.epam.jwd.dao.connection_pool.MariaDBConnectionPool;
import by.epam.jwd.dao.interf.CarDAO;
import by.epam.jwd.dao.interf.MotorDepotDAO;
import by.epam.jwd.dao.interf.UserDao;

public class MariaDBMotorDepotDAO implements MotorDepotDAO {

    private static final MariaDBMotorDepotDAO INSTANCE = new MariaDBMotorDepotDAO();
    private final MariaDBConnectionPool connectionPool = MariaDBConnectionPool.getConnectionPool();
    private static CarDAO carDAO = null;
    private static UserDao userDao = null;

    private MariaDBMotorDepotDAO(){}

    public static MotorDepotDAO getMySqlMotorDeportDao(){
        return INSTANCE;
    }

    @Override
    public CarDAO getCarDao() {
        if (carDAO == null) {
            carDAO = new MariaDBCarDAO();
        }
        return carDAO;
    }

    @Override
    public UserDao getUserDao() {
        if (userDao == null) {
            userDao = new MariaDBUserDAO();
        }
        return userDao;
    }

    @Override
    public void closeAllConnections() {
        connectionPool.clearConnectionQueue();
    }
}
