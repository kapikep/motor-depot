package by.epam.jwd.dao.impl;

import by.epam.jwd.dao.connection_pool.MariaDBConnectionPool;
import by.epam.jwd.dao.interf.CarDAO;
import by.epam.jwd.dao.interf.MotorDepotDAO;

public class MariaDBMotorDepotDAO implements MotorDepotDAO {

    private static final MariaDBMotorDepotDAO INSTANCE = new MariaDBMotorDepotDAO();
    private static CarDAO carDAO = null;
    private final MariaDBConnectionPool connectionPool = MariaDBConnectionPool.getConnectionPool();

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
    public void closeAllConnections() {
        connectionPool.clearConnectionQueue();
    }
}
