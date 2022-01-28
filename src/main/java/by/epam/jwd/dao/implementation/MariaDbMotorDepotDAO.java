package by.epam.jwd.dao.implementation;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.dao.connection_pool.MariaDBConnectionPool;
import by.epam.jwd.dao.interf.CarDAO;
import by.epam.jwd.dao.interf.MotorDepotDAOFactory;
import by.epam.jwd.dao.interf.OrderDAO;
import by.epam.jwd.dao.interf.UserDao;

public class MariaDbMotorDepotDAO implements MotorDepotDAOFactory {

    private static final MariaDbMotorDepotDAO INSTANCE = new MariaDbMotorDepotDAO();
    private static final MariaDBConnectionPool connectionPool = MariaDBConnectionPool.getConnectionPool();
    private static final CarDAO CAR_DAO = new MariaDbCarDAO();
    private static final UserDao USER_DAO = new MariaDbUserDAO();
    private static final OrderDAO ORDER_DAO = new MariaDbOrderDAO();

    private MariaDbMotorDepotDAO(){}

    public static MotorDepotDAOFactory getMySqlMotorDeportDao(){
        return INSTANCE;
    }

    @Override
    public CarDAO getCarDao() {
        return CAR_DAO;
    }

    @Override
    public  UserDao getUserDao() {
        return USER_DAO;
    }

    @Override
    public OrderDAO getOrderDao() {
        return ORDER_DAO;
    }

    @Override
    public void closeAllConnections() throws DAOException {
        connectionPool.closeConnectionQueue();
    }
}
