package by.epam.jwd.service.interf;

import by.epam.jwd.entity.Driver;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.ValidateException;

import java.util.List;
import java.util.Map;

public interface DriverService {

    void createDriver(Map<String, String> param) throws ServiceException, ValidateException;

    List<Driver> readDrivers() throws ServiceException;

    int getDriverPageCount(String rowLimitStr) throws ServiceException;

    List<Integer> pagination(String pageStr, String rowLimitStr) throws ServiceException;

    List<Driver> readDrivers(String pageStr, String rowLimitStr) throws ServiceException;

    Driver readDriver(String idStr) throws ServiceException;

    List<Driver> findDrivers(String param, String value) throws ServiceException;

    List<Driver> findDrivers(Map<String, String> criteriaMap) throws ServiceException;

    void updateDriver(Map<String, String> criteriaMap) throws ServiceException, ValidateException;

    Driver createDriverEntity(Map<String, String> param) throws ServiceException;
}
