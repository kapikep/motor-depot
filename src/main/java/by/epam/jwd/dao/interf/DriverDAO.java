package by.epam.jwd.dao.interf;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.entity.Driver;

import java.util.List;
import java.util.Map;

public interface DriverDAO {


    void createDriver(Driver Driver) throws DAOException;

    Driver readDriver(int id) throws DAOException;

    List<Driver> readDrivers() throws DAOException;

    List<Driver> readDrivers(int page, int limit) throws DAOException;

    List<Driver> findDrivers(int page, int limit, String whereParam, String whereValue) throws DAOException;

    List<Driver> readDrivers(int page, int limit, String orderBy) throws DAOException;

    List<Driver> findDrivers(String whereParam, String whereValue) throws DAOException;

    List<Driver> findDrivers(Map<String, String> criteriaMap) throws DAOException;

    int getDriversSize() throws DAOException;

    void updateDriver(Driver Driver) throws DAOException;
}
