package by.epam.jwd.service.interf;

import by.epam.jwd.entity.Car;
import by.epam.jwd.entity.CarModel;
import by.epam.jwd.service.ServiceException;

import java.util.List;
import java.util.Map;

public interface CarService {

    boolean createModel(String modelName, String type, String loadCapacityStr, String passengerCapacityStr, String wheelDriveType) throws ServiceException;

    boolean createCar(String licencePlate, String color, String photo, String odometrStr, String status, String carModelIdStr) throws ServiceException;

    List<Car> readCars() throws ServiceException;

    int getCarPageCount(String rowLimit) throws ServiceException;

    List<Integer> pagination(String rowLimitStr, String pageStr) throws ServiceException;

    List<Car> readCars(String pageStr, String limitStr) throws ServiceException;

    Car readCar(String idStr) throws ServiceException;

    Car readCar(int id) throws ServiceException;

    List<Car> findCars(String param, String value) throws ServiceException;

    List<CarModel> readCarModels() throws ServiceException;

    List<Car> findCars(Map<String, String> criteriaMap) throws ServiceException;

    List<String> readCarTypes() throws ServiceException;

    boolean updateCar(String id, String licencePlate, String color, String photo, String odometrStr, String status, String carModelIdStr) throws ServiceException;

    void updateCar(Car car) throws ServiceException;

    List<Car> findFreeCars(Map<String, String> criteriaCar, Map<String, String> criteriaOrder) throws ServiceException;

    boolean deleteCar(String idStr) throws ServiceException;
}
