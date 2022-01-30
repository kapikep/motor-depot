package by.epam.jwd.dao.interf;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.entity.Car;
import by.epam.jwd.entity.CarModel;

import java.util.List;
import java.util.Map;

public interface CarDAO {

    boolean createCar (Car car) throws DAOException;

    boolean createModel(CarModel carModel) throws DAOException;

    List<Car> readCars() throws DAOException;

    int getCarSize() throws DAOException;

    List<Car> readCars(int page, int limit) throws DAOException;

    List<Car> readCars(int page, int limit, String orderBy) throws DAOException;

    List<String> readCarTypes() throws DAOException;

    List<CarModel> readCarModels() throws DAOException;

    List<Car> findCars(String param, String value) throws DAOException;

    Car readCar(int id) throws DAOException;

    boolean deleteCar(int id) throws DAOException;

    List<CarModel> readCarModels(String whereParam, String whereValue) throws DAOException;

    boolean updateCar(Car car) throws DAOException;

    List<Car> findCars(Map<String, String> criteriaMap) throws DAOException;
}
