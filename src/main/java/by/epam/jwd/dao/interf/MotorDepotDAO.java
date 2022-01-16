package by.epam.jwd.dao.interf;

public interface MotorDepotDAO {

    CarDAO getCarDao();

    void closeAllConnections();
}
