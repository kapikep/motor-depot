package by.epam.jwd.dao.interf;

import by.epam.jwd.dao.DAOException;

public interface MotorDepotDAOFactory {

    CarDAO getCarDao();

    UserDao getUserDao();

    OrderDAO getOrderDao();

    void closeAllConnections() throws DAOException;
}
