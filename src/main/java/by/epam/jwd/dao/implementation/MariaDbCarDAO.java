package by.epam.jwd.dao.implementation;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.dao.connection_pool.MariaDBConnectionPool;
import by.epam.jwd.dao.interf.CarDAO;
import by.epam.jwd.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MariaDbCarDAO implements CarDAO {

    private final MariaDBConnectionPool CONNECTION_POOL = MariaDBConnectionPool.getConnectionPool();
    private final String CREATE_CAR = "INSERT INTO cars (licence_plate, color, car_photo, odometr, status, car_model_id)" +
            "VALUES(?, ?, ?, ?, ?, ?)";
    private final String CREATE_MODEL = "INSERT INTO car_model (model_name, `type`, load_capacity, passenger_capacity, wheel_drive_type) " +
            "VALUES(?, ?, ?, ?, ?)";
    private final String FIND_ALL_CAR = "SELECT * FROM cars JOIN car_model ON cars.car_model_id = car_model.id";
    private final String FIND_CAR = "SELECT * FROM cars JOIN car_model ON cars.car_model_id = car_model.id WHERE car_model.load_capacity>? AND cars.status=?";
    private final String GET_CAR = "SELECT * FROM cars JOIN car_model ON cars.car_model_id = car_model.id WHERE cars.id=?";
    private final String FIND_CAR_MODEL = "SELECT * FROM car_model";
    private final String UPDATE_CAR = "UPDATE cars SET licence_plate=?, color=?, car_photo=?, odometr=?, status=?, car_model_id=? WHERE id=?";
    private final String DELETE_CAR = "DELETE FROM cars WHERE id=?";

    @Override
    public List<Car> findCars(Map<String, String> criteriaMap) throws DAOException {
        Car car = null;
        List<Car> cars = new ArrayList<>();
        try {
            Connection connection = CONNECTION_POOL.takeConnection();
            PreparedStatement ps = connection.prepareStatement(FIND_CAR);
            ps.setInt(1, 0);
            ps.setString(2, "active");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                cars.add(buildCar(resultSet));
            }

            CONNECTION_POOL.returnConnection(connection, ps);

        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return cars;
    }

    @Override
    public Car readCar(int id) throws DAOException {
        Car car = null;
        try {
            Connection connection = CONNECTION_POOL.takeConnection();
            PreparedStatement ps = connection.prepareStatement(GET_CAR);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                car = buildCar(resultSet);
            }

            CONNECTION_POOL.returnConnection(connection, ps);

        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return car;
    }

    @Override
    public boolean createCar(Car car) throws DAOException {
        boolean result = false;
        try {
            int count;
            Connection connection = CONNECTION_POOL.takeConnection();
            PreparedStatement ps = connection.prepareStatement(CREATE_CAR);
            ps.setString(1, car.getLicencePlate());
            ps.setString(2, car.getColor());
            ps.setString(3, car.getPhoto());
            ps.setInt(4, car.getOdometr());
            ps.setString(5, car.getStatus());
            ps.setInt(6, car.getCarModelId());

            count = ps.executeUpdate();
            CONNECTION_POOL.returnConnection(connection, ps);
            if(count == 1){
                result = true;
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return result;
    }

    @Override
    public boolean createMadel(CarModel carModel) throws DAOException {
        boolean result = false;
        try {
            int count;
            Connection connection = CONNECTION_POOL.takeConnection();
            PreparedStatement ps = connection.prepareStatement(CREATE_MODEL);
            ps.setString(1, carModel.getModelName());
            ps.setString(2, carModel.getType());
            ps.setInt(3, carModel.getLoadCapacity());
            ps.setInt(4, carModel.getPassengerCapacity());
            ps.setString(5, carModel.getWheelDriveType());

            count = ps.executeUpdate();
            CONNECTION_POOL.returnConnection(connection, ps);
            if(count == 1){
                result = true;
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return result;
    }

    @Override
    public List<Car> readAllCar() throws DAOException {
        List<Car> cars = new ArrayList<>();

        try {
            Connection connection = CONNECTION_POOL.takeConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(FIND_ALL_CAR);

            while (resultSet.next()) {
                cars.add(buildCar(resultSet));
            }
            CONNECTION_POOL.returnConnection(connection, statement, resultSet);

        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return cars;
    }

    @Override
    public List<CarModel> readAllCarModels() throws DAOException {
        List<CarModel> cars = new ArrayList<>();

        try {
            Connection connection = CONNECTION_POOL.takeConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement
                    .executeQuery(FIND_CAR_MODEL);

            while (resultSet.next()) {

                cars.add(new CarModel(resultSet.getInt("id"),
                        resultSet.getString("model_name"), resultSet.getString("type"),
                        resultSet.getInt("load_capacity"), resultSet.getInt("passenger_capacity"),
                        resultSet.getString("wheel_drive_type")));
            }

            CONNECTION_POOL.returnConnection(connection, statement, resultSet);

        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return cars;
    }


    @Override
    public boolean updateCar(Car car) throws DAOException {

        boolean result = false;
        try {
            int count;
            Connection connection = CONNECTION_POOL.takeConnection();
            PreparedStatement ps = connection.prepareStatement(UPDATE_CAR);
            ps.setString(1, car.getLicencePlate());
            ps.setString(2, car.getColor());
            ps.setString(3, car.getPhoto());
            ps.setInt(4, car.getOdometr());
            ps.setString(5, car.getStatus());
            ps.setInt(6, car.getCarModelId());
            ps.setInt(7, car.getId());

            count = ps.executeUpdate();
            CONNECTION_POOL.returnConnection(connection, ps);
            if(count == 1){
                result = true;
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return result;

    }

    @Override
    public boolean deleteCar(int id) throws DAOException {
        boolean result = false;
        try {
            int count;
            Connection connection = CONNECTION_POOL.takeConnection();
            PreparedStatement ps = connection.prepareStatement(DELETE_CAR);
            ps.setInt(1, id);
            count = ps.executeUpdate();
            CONNECTION_POOL.returnConnection(connection, ps);
            if(count == 1){
                result = true;
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return result;
    }

    private Car buildCar(ResultSet resultSet) throws SQLException {
        return new Car(resultSet.getInt("id"), resultSet.getString("licence_plate"),
                resultSet.getString("color"), resultSet.getInt("car_model_id"),
                resultSet.getString("model_name"), resultSet.getString("type"),
                resultSet.getInt("load_capacity"), resultSet.getInt("passenger_capacity"),
                resultSet.getString("wheel_drive_type"), resultSet.getInt("odometr"),
                resultSet.getString("status"), resultSet.getString("car_photo"));
    }
}
