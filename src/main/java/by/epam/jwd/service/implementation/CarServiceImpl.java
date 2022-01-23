package by.epam.jwd.service.implementation;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.dao.implementation.MariaDBMotorDepotDAO;
import by.epam.jwd.dao.interf.CarDAO;
import by.epam.jwd.dao.interf.MotorDepotDAO;
import by.epam.jwd.entity.Car;
import by.epam.jwd.entity.CarModel;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.interf.CarService;

import java.util.List;

public class CarServiceImpl implements CarService {
    private final MotorDepotDAO MOTOR_DEPORT_DAO = MariaDBMotorDepotDAO.getMySqlMotorDeportDao();
    private final CarDAO CAR_DAO = MOTOR_DEPORT_DAO.getCarDao();

    @Override
    public boolean createMadel(CarModel carModel) throws ServiceException {
        boolean result;
        try {
            //TODO validate
            result = CAR_DAO.createMadel(carModel);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean createCar(Car car) throws ServiceException {
        boolean result;
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
}
