package by.epam.jwd.dao;

import java.io.IOException;
import java.sql.*;

import by.epam.jwd.dao.impl.MariaDBMotorDepotDAO;
import by.epam.jwd.dao.interf.CarDAO;
import by.epam.jwd.dao.interf.MotorDepotDAO;

public class TestConnection {

	public static void main(String[] args) throws InterruptedException, IOException {

//      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//      String input;
//
////      do{
////          input = bufferedReader.readLine();
////
////      }while(!input.equals("stop"));

		MotorDepotDAO motorDepotDAO = MariaDBMotorDepotDAO.getMySqlMotorDeportDao();
		CarDAO carDAO = motorDepotDAO.getCarDao();
		try {
			System.out.println(carDAO.readAllCar());
			Thread.sleep(10000);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("stop");
		// TestConnection.testConnection();

	}

	public static void testConnection() {
		Connection connection = null;
		ResultSet resultSet = null;
		Statement statement = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Motor_deport", "root", "root");

			statement = connection.createStatement();

			resultSet = statement.executeQuery("SELECT * FROM cars");
			while (resultSet.next()) {
				System.out.println(resultSet.getString("licence_plate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
