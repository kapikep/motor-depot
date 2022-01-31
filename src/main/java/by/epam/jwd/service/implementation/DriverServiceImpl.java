package by.epam.jwd.service.implementation;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.dao.MotorDepotDAOFactory;
import by.epam.jwd.dao.interf.DriverDAO;
import by.epam.jwd.entity.Driver;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.ServiceUtil;
import by.epam.jwd.service.interf.DriverService;

import java.util.List;
import java.util.Map;

public class DriverServiceImpl implements DriverService {

    private final DriverDAO DRIVER_DAO = MotorDepotDAOFactory.getMotorDepotDAOFactory().getDriverDao();

    @Override
    public void createDriver(Map<String, String> param) throws ServiceException {
        Driver driver = null;
        try {
            //TODO validate
            DRIVER_DAO.createDriver(driver);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Driver> readDrivers() throws ServiceException {
        List<Driver> Drivers;
        try {
            Drivers = DRIVER_DAO.readDrivers();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return Drivers;
    }

    @Override
    public int getDriverPageCount(String rowLimitStr) throws ServiceException {
        int size;
        int rowLimit = ServiceUtil.parseInt(rowLimitStr, 10);

        try {
            size = DRIVER_DAO.getDriversSize();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        size = (int) Math.ceil((double) size / rowLimit);
        return size;
    }

    @Override
    public List<Integer> pagination(String pageStr, String rowLimitStr) throws ServiceException {
        int rowLimit = ServiceUtil.parseInt(rowLimitStr, 10);
        int page = ServiceUtil.parseInt(pageStr);
        List<Integer> res;
        int pageCount = getDriverPageCount(rowLimitStr);
        res = ServiceUtil.getPagesNumber(pageCount, rowLimit, page);
        return res;
    }

    @Override
    public List<Driver> readDrivers(String pageStr, String rowLimitStr) throws ServiceException {
        List<Driver> Drivers;
        int page = ServiceUtil.parseInt(pageStr);
        int rowLimit = ServiceUtil.parseInt(rowLimitStr, 10);

        try {
            Drivers = DRIVER_DAO.readDrivers(page, rowLimit);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return Drivers;
    }

    @Override
    public Driver readDriver(String idStr) throws ServiceException {
        Driver driver = null;
        int id = ServiceUtil.parseInt(idStr);
        try {
            driver = DRIVER_DAO.readDriver(id);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return driver;
    }

    @Override
    public List<Driver> findDrivers(String param, String value) throws ServiceException {
        List<Driver> Drivers;
        try {
            Drivers = DRIVER_DAO.findDrivers(param, value);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return Drivers;
    }

    @Override
    public List<Driver> findDrivers(Map<String, String> criteriaMap) throws ServiceException {

        List<Driver> Drivers = null;
        try {
            Drivers = DRIVER_DAO.findDrivers(criteriaMap);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return Drivers;
    }

    @Override
    public void updateDriver(Map<String, String> criteriaMap) throws ServiceException {
        Driver driver = null;
        try {
            //TODO validate
            DRIVER_DAO.updateDriver(driver);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
