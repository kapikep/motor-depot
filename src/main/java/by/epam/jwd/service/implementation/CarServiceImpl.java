package by.epam.jwd.service.implementation;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.dao.implementation.MariaDbMotorDepotDAO;
import by.epam.jwd.dao.interf.CarDAO;
import by.epam.jwd.dao.interf.MotorDepotDAO;
import by.epam.jwd.entity.Car;
import by.epam.jwd.entity.CarModel;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.interf.CarService;

import java.util.List;

public class CarServiceImpl implements CarService {
    private final MotorDepotDAO MOTOR_DEPORT_DAO = MariaDbMotorDepotDAO.getMySqlMotorDeportDao();
    private final CarDAO CAR_DAO = MOTOR_DEPORT_DAO.getCarDao();

    @Override
    public boolean createMadel(String modelName, String type, String loadCapacityStr, String passengerCapacityStr, String wheelDriveType) throws ServiceException {
        boolean result;
        int loadCapacity = Integer.parseInt(loadCapacityStr);
        int passengerCapacity = Integer.parseInt(passengerCapacityStr);
        CarModel carModel = new CarModel(modelName, type, loadCapacity, passengerCapacity, wheelDriveType);
        try {
            //TODO validate
            result = CAR_DAO.createMadel(carModel);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean createCar(String licencePlate, String color, String photo, String odometrStr, String status, String carModelIdStr) throws ServiceException {
        boolean result;
        //TODO validation
        int odometr = Integer.parseInt(odometrStr);
        int carModelId = Integer.parseInt(carModelIdStr);

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
    public List<Car> readAllCar() throws ServiceException {
        List<Car> cars;
        try {
            cars = CAR_DAO.readAllCar();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return cars;
    }

    public int getCarSize() throws ServiceException{
        int size = 0;
        try{
            size = CAR_DAO.getCarSize();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return size;
    }

    @Override
    public List<Car> readCarsWithOffset(int page, int limit) throws ServiceException {
        List<Car> cars;
        try {
            cars = CAR_DAO.readCarsWithOffset(page, limit);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return cars;
    }

    @Override
    public Car readCar(int id) throws ServiceException {
        Car car = null;
        try {
            car = CAR_DAO.readCar(id);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return car;
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
