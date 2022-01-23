package by.epam.jwd.service.interf;

import by.epam.jwd.entity.Car;
import by.epam.jwd.entity.CarModel;
import by.epam.jwd.service.ServiceException;

import java.util.List;

public interface CarService {

    boolean createMadel(String modelName, String type, String loadCapacityStr, String passengerCapacityStr, String wheelDriveType) throws ServiceException;

    boolean createCar(String licencePlate, String color, String photo, String odometrStr, String status, String carModelIdStr) throws ServiceException;

    List<Car> readAllCar() throws ServiceException;

    List<CarModel> readAllCarModel() throws ServiceException;
}
