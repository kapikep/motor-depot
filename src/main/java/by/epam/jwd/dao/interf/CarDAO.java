package by.epam.jwd.dao.interf;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.entity.Car;
import by.epam.jwd.entity.CarModel;
import by.epam.jwd.entity.Criteria;

import java.util.List;

public interface CarDAO {

    void createCar (String licencePlate, String color, CarModel carModel);

    List<Car> readAllCar() throws DAOException;

    List<Car> findCars (Criteria criteria);

    void updateCat (int id, String licencePlate, String color, CarModel carModel);

    void deleteCar (String licencePlate);

}
