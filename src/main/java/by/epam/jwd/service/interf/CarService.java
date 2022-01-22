package by.epam.jwd.service.interf;

import by.epam.jwd.entity.Car;
import by.epam.jwd.service.ServiceException;

import java.util.List;

public interface CarService {
    public List<Car> readAllCar() throws ServiceException;
}
