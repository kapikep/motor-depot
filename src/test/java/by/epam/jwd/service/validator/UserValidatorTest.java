package by.epam.jwd.service.validator;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.dao.connection_pool.MariaDBConnectionPool;
import by.epam.jwd.service.ValidateException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.*;

public class UserValidatorTest {

    @BeforeClass
    public static void beforeClass() throws Exception {
        MariaDBConnectionPool.initPool("testDb");
    }

    @Test
    public void userFieldValueValidate() {
        Map<String, String> map = Map.of("password", "", "login", "", "name", "");
        try {
            UserValidator.userFieldValueValidate(map);
        } catch (ValidateException e) {
            System.out.println(e.getMessage());
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void afterClass() throws Exception {
        MariaDBConnectionPool.closeConnectionQueue();
    }
}