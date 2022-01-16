package by.epam.jwd.dao.impl;

import by.epam.jwd.dao.connection_pool.ConnectionPoolException;
import by.epam.jwd.dao.connection_pool.MySqlConnectionPool;
import by.epam.jwd.dao.interf.CarDAO;
import by.epam.jwd.entity.Car;
import by.epam.jwd.entity.CarModel;
import by.epam.jwd.entity.Criteria;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySqlCarDAO implements CarDAO {

    private final MySqlConnectionPool connectionPool = MySqlConnectionPool.getPooledConnection();

    @Override
    public void createCar(String licencePlate, String color, CarModel carModel) {
    }

    @Override
    public List<Car> readAllCar() {
        List<Car> cars = new ArrayList<>();
        try {
            Connection connection = connectionPool.takeConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM cars JOIN car_model ON cars.car_model_id = car_model.id");

            while (resultSet.next()){

                cars.add(new Car(resultSet.getInt("id"), resultSet.getString("licence_plate"), resultSet.getString("color"),
                        resultSet.getInt("car_model_id"), resultSet.getString("model_name"), resultSet.getString("type"),
                        resultSet.getInt("load_capacity"), resultSet.getInt("passenger_capacity"), resultSet.getString("wheel_drive_type")));

//                System.out.println(resultSet.getString("id") + " | "+ resultSet.getString("licence_plate")
//                        + " | "+ resultSet.getString("color")+ " | "+ resultSet.getString("model_name") + " | "+ resultSet.getString("type"));
            }

            connectionPool.returnConnection(connection, statement, resultSet);

        } catch (ConnectionPoolException | SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    @Override
    public List<Car> findCars(Criteria criteria) {
        return null;
    }

    @Override
    public void updateCat(int id, String licencePlate, String color, CarModel carModel) {

    }

    @Override
    public void deleteCar(String licencePlate) {

    }
}
