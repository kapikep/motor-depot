package by.epam.jwd.dao.implementation;

import by.epam.jwd.dao.interf.DriverDao;
import by.epam.jwd.entity.Driver;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MariaDbDriverDAO implements DriverDao {



    private Driver buildDriver(ResultSet rs) throws SQLException {
        Driver driver = new Driver();
        return driver;
    }
}
