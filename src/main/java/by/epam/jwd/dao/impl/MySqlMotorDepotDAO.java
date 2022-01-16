package by.epam.jwd.dao.impl;

import by.epam.jwd.dao.connection_pool.MySqlConnectionPool;
import by.epam.jwd.dao.interf.CarDAO;
import by.epam.jwd.dao.interf.MotorDepotDAO;

public class MySqlMotorDepotDAO implements MotorDepotDAO {

    private static final MySqlMotorDepotDAO INSTANCE = new MySqlMotorDepotDAO();
    private static CarDAO carDAO = null;
    private final MySqlConnectionPool connectionPool = MySqlConnectionPool.getPooledConnection();

    private MySqlMotorDepotDAO(){}

    public static MotorDepotDAO getMySqlMotorDeportDao(){
        return INSTANCE;
    }

    @Override
    public CarDAO getCarDao() {
        if (carDAO == null) {
            carDAO = new MySqlCarDAO();
        }
        return carDAO;
    }

    @Override
    public void closeAllConnections() {
        connectionPool.clearConnectionQueue();
    }
}
