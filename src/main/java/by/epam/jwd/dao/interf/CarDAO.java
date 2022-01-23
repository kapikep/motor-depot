package by.epam.jwd.dao.interf;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.entity.Car;
import by.epam.jwd.entity.CarModel;
import by.epam.jwd.entity.Criteria;

import java.util.List;

public interface CarDAO {

    boolean createCar (Car car) throws DAOException;

    boolean createMadel (CarModel carModel) throws DAOException;

    List<Car> readAllCar() throws DAOException;

    List<CarModel> readAllCarModels() throws DAOException;

    List<Car> findCars (Criteria criteria);

    void updateCar(Car car);

    void deleteCar(int id);

}
