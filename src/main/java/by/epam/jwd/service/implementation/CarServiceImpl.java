package by.epam.jwd.service.implementation;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.dao.MotorDepotDAOFactory;
import by.epam.jwd.dao.interf.CarDAO;
import by.epam.jwd.entity.Car;
import by.epam.jwd.entity.CarModel;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.ServiceUtil;
import by.epam.jwd.service.interf.CarService;

import java.util.Arrays;
import java.util.List;

public class CarServiceImpl implements CarService {
    private final CarDAO CAR_DAO = MotorDepotDAOFactory.getMotorDepotDAOFactory().getCarDao();
    private final List<String> COLUMN_CAR_NAMES  = Arrays.asList("id", "licence_plate", "color", "car_photo", "odometr", "status", "car_model_id");
    private final List<String> COLUMN_CAR_MODEL_NAMES  = Arrays.asList("id", "model_name", "type", "load_capacity", "passenger_capacity", "wheel_drive_type");

    @Override
    public boolean createModel(String modelName, String type, String loadCapacityStr, String passengerCapacityStr, String wheelDriveType) throws ServiceException {
        boolean result;
        int loadCapacity = Integer.parseInt(loadCapacityStr);
        //TODO ServiceUtil
        int passengerCapacity = Integer.parseInt(passengerCapacityStr);
        CarModel carModel = new CarModel(modelName, type, loadCapacity, passengerCapacity, wheelDriveType);
        try {
            //TODO validate
            result = CAR_DAO.createModel(carModel);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return result;
    }


    @Override
    public boolean createCar(String licencePlate, String color, String photo, String odometrStr, String status, String carModelIdStr) throws ServiceException {
        boolean result;
        //TODO validation
        int odometr = ServiceUtil.parseInt(odometrStr);
        int carModelId = ServiceUtil.parseInt(carModelIdStr);

        Car car = new Car(licencePlate, color, photo, odometr, status, carModelId);
        try {
            //TODO validate
            result = CAR_DAO.createCar(car);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public List<Car> readCars() throws ServiceException {
        List<Car> cars;
        try {
            cars = CAR_DAO.readAllCar();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return cars;
    }

    @Override
    public int getCarPageCount(String rowLimitStr) throws ServiceException{
        int size;
        int rowLimit = ServiceUtil.parseInt(rowLimitStr, 10);

        try{
            size = CAR_DAO.getCarSize();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        size = (int) Math.ceil((double) size/rowLimit);
        return size;
    }

    @Override
    public List<Integer> pagination(String pageStr, String rowLimitStr) throws ServiceException {
        int rowLimit = ServiceUtil.parseInt(rowLimitStr, 10);
        int page = ServiceUtil.parseInt(pageStr);
        List<Integer> res;
        int pageCount = getCarPageCount(rowLimitStr);
        res = ServiceUtil.pagination(pageCount, rowLimit, page);
        return res;
    }

    @Override
    public List<Car> readCars(String pageStr, String rowLimitStr) throws ServiceException {
        List<Car> cars;
        int page = ServiceUtil.parseInt(pageStr);
        int rowLimit = ServiceUtil.parseInt(rowLimitStr, 10);

        try {
            cars = CAR_DAO.readCars(page, rowLimit);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return cars;
    }

    @Override
    public Car readCar(String idStr) throws ServiceException {
        Car car = null;
        int id = ServiceUtil.parseInt(idStr);
        try {
            car = CAR_DAO.readCar(id);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return car;
    }

    @Override
    public List<Car> findCars(String param, String value) throws ServiceException {
        List<Car> cars;
        try {
            cars = CAR_DAO.findCars(param, value);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return cars;
    }


    @Override
    public List<CarModel> readAllCarModel() throws ServiceException {
        List<CarModel> cars;
        try {
            cars = CAR_DAO.readAllCarModels();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return cars;
    }

    @Override
    public boolean updateCar(String idStr, String licencePlate, String color, String photo, String odometrStr, String status, String carModelIdStr) throws ServiceException {
        boolean result;
        int odometr = Integer.parseInt(odometrStr);
        int carModelId = Integer.parseInt(carModelIdStr);
        int id = Integer.parseInt(idStr);
        Car car = new Car(id, licencePlate, color, photo, odometr, status, carModelId);

        try {
            //TODO validate
            result = CAR_DAO.updateCar(car);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean deleteCar(String idStr) throws ServiceException{
        boolean result;
        int id = Integer.parseInt(idStr);
        try {
            result = CAR_DAO.deleteCar(id);
        } catch (DAOException e) {
           throw new ServiceException(e);
        }
        return result;
    }
}
