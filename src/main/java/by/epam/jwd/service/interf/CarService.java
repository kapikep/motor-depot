package by.epam.jwd.service.interf;

import by.epam.jwd.entity.Car;
import by.epam.jwd.entity.CarModel;
import by.epam.jwd.service.ServiceException;

import java.util.List;

public interface CarService {

    List<Car> readAllCar() throws ServiceException;
    boolean createMadel(CarModel carModel) throws ServiceException;
    boolean createCar(Car car) throws ServiceException;

    List<CarModel> readAllCarModel() throws ServiceException;
}
