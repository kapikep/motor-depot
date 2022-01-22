package by.epam.jwd.service.implementation;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.dao.implementation.MariaDBMotorDepotDAO;
import by.epam.jwd.dao.interf.CarDAO;
import by.epam.jwd.dao.interf.MotorDepotDAO;
import by.epam.jwd.entity.Car;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.interf.CarService;

import java.util.List;

public class CarServiceImpl implements CarService {

    public List<Car> readAllCar() throws ServiceException {
        List<Car> cars;
        MotorDepotDAO motorDepotDAO = MariaDBMotorDepotDAO.getMySqlMotorDeportDao();
        CarDAO carDAO = motorDepotDAO.getCarDao();
        try {
            cars = carDAO.readAllCar();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return cars;
    }
}
