package by.epam.jwd.dao.interf;

public interface MotorDepotDAO {

    CarDAO getCarDao();

    UserDao getUserDao();

    void closeAllConnections();
}
