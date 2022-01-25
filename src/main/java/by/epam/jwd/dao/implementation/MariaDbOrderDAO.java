package by.epam.jwd.dao.implementation;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.dao.connection_pool.MariaDBConnectionPool;
import by.epam.jwd.dao.interf.OrderDAO;
import by.epam.jwd.entity.Order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MariaDbOrderDAO implements OrderDAO {

    private final MariaDBConnectionPool CONNECTION_POOL = MariaDBConnectionPool.getConnectionPool();

    public List<Order> readOrders() throws DAOException {
        List<Order> cars = new ArrayList<>();

        try {
            Connection connection = CONNECTION_POOL.takeConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM cars JOIN car_model ON cars.car_model_id = car_model.id");

            while (resultSet.next()) {

                cars.add(new Order());
            }

            CONNECTION_POOL.returnConnection(connection, statement, resultSet);

        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return cars;
    }
}
