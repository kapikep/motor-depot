package by.epam.jwd.service.implementation;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.dao.MotorDepotDAOFactory;
import by.epam.jwd.dao.interf.DriverDAO;
import by.epam.jwd.dao.interf.UserDao;
import by.epam.jwd.entity.Driver;
import by.epam.jwd.entity.Role;
import by.epam.jwd.entity.Status;
import by.epam.jwd.entity.User;
import by.epam.jwd.service.MDServiceFactory;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.ServiceUtil;
import by.epam.jwd.service.ValidateException;
import by.epam.jwd.service.interf.DriverService;
import by.epam.jwd.service.interf.UserService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class DriverServiceImpl implements DriverService {

    private final DriverDAO DRIVER_DAO = MotorDepotDAOFactory.getMotorDepotDAOFactory().getDriverDao();
    private final UserService USER_SERVICE = MDServiceFactory.getMDService().getUserService();

    @Override
    public void createDriver(Map<String, String> param) throws ServiceException, ValidateException {
        Driver driver = null;
        try {
            driver = createDriverEntity(param);
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
           throw new ServiceException(e);
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
        List<Driver> Drivers;
        try {
            Drivers = DRIVER_DAO.findDrivers(criteriaMap);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return Drivers;
    }

    @Override
    public void updateDriver(Map<String, String> param) throws ServiceException, ValidateException {
        Driver driver;
        try {
            driver = createDriverEntity(param);
            DRIVER_DAO.updateDriver(driver);
            param.put("id", param.get("userId"));
            param.put("role", Role.DRIVER.toString());
            System.out.println(param.get("prevUserLogin"));
            USER_SERVICE.updateUser(param);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Driver createDriverEntity(Map<String, String> param) throws ServiceException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Driver driver = new Driver();

        String id = param.get("id");
        if (id != null && !("".equals(id))) {
            driver.setId(Integer.parseInt(id));
        }
        String userId = param.get("userId");
        if (userId != null && !("".equals(userId))) {
            driver.setUserId(Integer.parseInt(userId));
        }
        driver.setName(param.get("name"));
        driver.setSurname(param.get("surname"));
        driver.setLogin(param.get("login"));
        driver.setPassword(param.get("password"));
        driver.setPhoneNumber(param.get("phoneNumber"));
        driver.setStatus(Status.valueOf(param.get("status")));
        driver.seteMail(param.get("eMail"));
        driver.setAdditionalInfo(param.get("additionalInfo"));
        driver.setCategory(param.get("category"));
        String carIdStr = param.get("attachedCarId");
        driver.setRole(Role.DRIVER);

        if (carIdStr != null && !("".equals(carIdStr))) {
            driver.setAttachedCarId(Integer.parseInt(carIdStr));
        }
        String drExpStr = param.get("drivingExperience");
        if (drExpStr != null && !("".equals(drExpStr))) {
            driver.setDrivingExperience(Integer.parseInt(drExpStr));
        }
        try {
            String emplDateStr = param.get("dateOfEmployment");
            if (emplDateStr != null && !("".equals(emplDateStr))) {
                driver.setDateOfEmployment(sdf.parse(emplDateStr));
            }
            String dismDateStr  = param.get("dateOfDismissal");

            if (dismDateStr != null && !("".equals(dismDateStr))) {
                driver.setDateOfDismissal(sdf.parse(dismDateStr));
            }
        } catch (ParseException e) {
            throw new ServiceException(e);
        }
        return driver;
    }
}
