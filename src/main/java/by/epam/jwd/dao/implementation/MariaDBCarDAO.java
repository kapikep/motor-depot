package by.epam.jwd.dao.implementation;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.dao.connection_pool.MariaDBConnectionPool;
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

public class MariaDBCarDAO implements CarDAO {

	private final MariaDBConnectionPool connectionPool = MariaDBConnectionPool.getConnectionPool();



	@Override
	public boolean createCar(Car car) {
		return false;
	}

	@Override
	public boolean createMadel(CarModel carModel) {
		return false;
	}


	@Override
	public List<Car> readAllCar() throws DAOException {
		List<Car> cars = new ArrayList<>();

		try {
			Connection connection = connectionPool.takeConnection();
			Statement statement = connection.createStatement();

			ResultSet resultSet = statement
					.executeQuery("SELECT * FROM cars JOIN car_model ON cars.car_model_id = car_model.id");

			while (resultSet.next()) {

				cars.add(new Car(resultSet.getInt("id"), resultSet.getString("licence_plate"),
						resultSet.getString("color"), resultSet.getInt("car_model_id"),
						resultSet.getString("model_name"), resultSet.getString("type"),
						resultSet.getInt("load_capacity"), resultSet.getInt("passenger_capacity"),
						resultSet.getString("wheel_drive_type"), resultSet.getString("odometr"),
						resultSet.getString("status"), resultSet.getString("car_photo")));
			}

			connectionPool.returnConnection(connection, statement, resultSet);

		} catch (SQLException e) {
			throw new DAOException(e);
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
