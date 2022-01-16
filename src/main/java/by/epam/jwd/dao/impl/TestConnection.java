package by.epam.jwd.dao.impl;

import java.sql.*;

public class TestConnection {
    public static void testConnection(){
        Connection connection = null;
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Motor_deport",
                    "root", "root");

            statement = connection.createStatement();

            resultSet = statement.executeQuery("SELECT * FROM cars");
            while (resultSet.next()){
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
