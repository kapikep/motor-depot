package by.epam.jwd.dao.implementation;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.dao.MotorDepotDAOFactory;
import by.epam.jwd.dao.connection_pool.MariaDBConnectionPool;
import by.epam.jwd.dao.interf.UserDao;
import by.epam.jwd.entity.Role;
import by.epam.jwd.entity.Status;
import by.epam.jwd.entity.User;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class MariaDbUserDAOTest {
    UserDao userDao = MotorDepotDAOFactory.getMotorDepotDAOFactory().getUserDao();
    User user = new User();
    User userRes;
    int limit = 7;
    int page = 1;
    int tableSize = 8;

    @BeforeClass
    public static void beforeClass() throws Exception {
        MariaDBConnectionPool.initPool("testDb");
    }

//    @Test
//    public void createUserTest() {
//        user = new User("Ivan", "Markov", "Ivan", "driver", "+375336767755", "nan", Status.ACTIVE,
//                "mymail@gmail.com", "Minsk", Role.DRIVER);
//        try{
//            userDao.createUser(user);
//            userRes = userDao.authorization("Vasilij", "customer");
//        } catch (DAOException e) {
//            e.printStackTrace();
//        }
//        Assert.assertEquals(user.getLogin(), userRes.getLogin());
//    }

    @Test
    public void updateUserTest() {
        user = new User("Ivan", "Markov", "Ivan", "driver", "+375116767755", "nan", Status.ACTIVE,
                "mymail@gmail.com", "Borisov", Role.DRIVER);
        try{
            userDao.updateUser(user);
            userRes = userDao.authorization("Ivan", "driver");
        } catch (DAOException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(user.getAdditionalInfo(), userRes.getAdditionalInfo());
    }

    @Test
    public void authorizationTest() throws DAOException {
        try {
            user = userDao.authorization("Petr", "driver");
        } catch (DAOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Assert.assertEquals("Petr", user.getName());
    }

    @Test
    public void readUserTest() {
        try{
            user = userDao.readUser(2);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        Assert.assertEquals("Petr", user.getLogin());
    }

    @Test
    public void readUsersTest() {
        List<User> users = null;
        try {
            users = userDao.readUsers();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(tableSize, users.size());
    }

    @Test
    public void readUsersTest1() {
        List<User> users = null;
        try {
            users = userDao.readUsers(page, limit);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(limit, users.size());
    }

    @Test
    public void readUsersTest2() {
        List<User> users = null;
        try {
            users = userDao.readUsers(page, limit, "name");
        } catch (DAOException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(limit, users.size());
    }

    @Test
    public void findUsersTest() {
        List<User> users = null;
        try {
            users = userDao.findUsers("status", "ACTIVE" );
        } catch (DAOException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(6, users.size());
        Assert.assertEquals(Status.ACTIVE, users.get(0).getStatus());
    }

    @Test
    public void findUsersTest1() {
        List<User> users = null;
        try {
            users = userDao.findUsers("roles_id", "2" );
        } catch (DAOException e) {
            e.printStackTrace();
        }
        //System.out.println(users);
    }

    @Test
    public void findUsersTest2() {
        List<User> users = null;
        try {
            users = userDao.findUsers(1, 2,"status", "ACTIVE" );
        } catch (DAOException e) {
            e.printStackTrace();
        }
        //System.out.println(users);
    }

    @Test
    public void readUsersSize() {
        int size = 0;
        try {
            size = userDao.getUsersSize();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(tableSize, size);
    }

    @AfterClass
    public static void afterClass() throws Exception {
        MariaDBConnectionPool.closeConnectionQueue();
    }
}