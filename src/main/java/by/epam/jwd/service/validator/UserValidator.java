package by.epam.jwd.service.validator;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.dao.MotorDepotDAOFactory;
import by.epam.jwd.dao.interf.UserDao;
import by.epam.jwd.entity.User;
import by.epam.jwd.service.ValidateException;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {

    private static final UserDao userDao = MotorDepotDAOFactory.getMotorDepotDAOFactory().getUserDao();

    private final List<String> COLUMN_CAR_NAMES = Arrays.asList("id", "name", "surname", "login", "password", "phone_number", "photo", "status", "e-mail", "additionalInfo", "roles_id");

    public static boolean loginValidate (String login) throws ValidateException, DAOException {
        List<User>users = null;
        users = userDao.findUsers("login", login);
        if(!users.isEmpty()){
            throw new ValidateException("Login is exist");
        }
        return true;
    }

    public static void passwordValidate (String password) throws ValidateException{
        if(password.isEmpty()){
            throw new ValidateException("Empty password");
        }
    }

    public static void phoneValidate (String phone) throws ValidateException{

        if(phone.isEmpty()){
            throw new ValidateException("Empty phone");
        }

        Pattern pattern = Pattern.compile("\\+?\\d{1,15}");
        Matcher matcher = pattern.matcher(phone);
        if(!matcher.matches()){
            throw new ValidateException("Incorrect phone");
        }
    }


}
