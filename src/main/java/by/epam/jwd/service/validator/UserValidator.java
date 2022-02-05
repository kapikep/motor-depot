package by.epam.jwd.service.validator;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.dao.MotorDepotDAOFactory;
import by.epam.jwd.dao.interf.UserDao;
import by.epam.jwd.entity.Role;
import by.epam.jwd.entity.Status;
import by.epam.jwd.entity.User;
import by.epam.jwd.service.ValidateException;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {

    private static final UserDao userDao = MotorDepotDAOFactory.getMotorDepotDAOFactory().getUserDao();
    private static final ResourceBundle bundle = ResourceBundle.getBundle("local");


    public static String idValidate(String id) throws ValidateException {
        String resMes = "All ok";
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(id);

        if(!(matcher.matches())){
            resMes = "Id not digit";
        } else if (id.length() > 20) {
            resMes = "Id is to long";
        }
        return resMes;
    }

    public static String loginValidate(String login) throws ValidateException, DAOException {
        List<User> users = null;
        String resMes = "All ok";
        if (login.isEmpty()) {
            resMes = "Login is empty";
        } else if (login.length() > 20) {
            resMes = "Login is too long";
        } else {
            users = userDao.findUsers("login", login);
            if (!users.isEmpty()) {
                resMes = users.get(0).getLogin() + " login exists";
            }
        }
        return resMes;
    }

    public static String passwordValidate(String password) throws ValidateException {
        String resMes = "All ok";
        if (password.isEmpty()) {
            resMes = "Password is empty";
        } else if (password.length() > 20) {
            resMes = "Password is too long";
        } else if ((password.length() < 6)) {
            resMes = "Password less than 6 chidaracters";
        }
        return resMes;
    }

    public static String phoneValidate(String phone) throws ValidateException {
        String resMes = "All ok";
        Pattern pattern = Pattern.compile("\\+?\\d{1,15}");
        Matcher matcher = pattern.matcher(phone);

        if (phone.isEmpty()) {
            resMes = "Empty phone";
        } else if (!matcher.matches()) {
            resMes = "Incorrect phone";
        }
        return resMes;
    }

    public static String nameValidate(String name) throws ValidateException {
        String resMes = "All ok";
        Pattern pattern = Pattern.compile("([A-Z]|[А-Я])([a-z]|[а-я])+");
        Matcher matcher = pattern.matcher(name);

        if (name.isEmpty()) {
            resMes = "Name is empty";
        } else if (name.length() > 20) {
            resMes = "Name is too long";
        } else if (!matcher.matches()){
            resMes = "Incorrect name";
        }
        return resMes;
    }

    public static String surnameValidate(String surname) throws ValidateException {
        String resMes = "All ok";
        Pattern pattern = Pattern.compile("([A-Z]|[А-Я])([a-z]|[а-я])+");
        Matcher matcher = pattern.matcher(surname);

        if (surname.isEmpty()) {
            resMes = "Surname is empty";
        } else if (surname.length() > 20) {
            resMes = "Surname is too long";
        } else if (!matcher.matches()){
            resMes = "Incorrect Surname";
        }
        return resMes;
    }

    public static String statusValidate(String status) throws ValidateException {
        String resMes = "All ok";

        if (status.isEmpty()) {
            resMes = "Status is empty";
        } else if (status.length() > 10) {
            resMes = "Status is too long";
        } else {
            try {
                Status.valueOf(status);
            } catch (IllegalArgumentException e) {
                throw new ValidateException("Illegal status");
            }
        }
        return resMes;
    }

    public static String roleValidate(String role) throws ValidateException {
        String resMes = "All ok";

        if (role.isEmpty()) {
            resMes = "Role is empty";
        } else if (role.length() > 10) {
            resMes = "Role is too long";
        } else {
            try {
                Role.valueOf(role);
            } catch (IllegalArgumentException e) {
                throw new ValidateException("Illegal role");
            }
        }
        return resMes;
    }

    public static String eMailValidate(String eMail) throws ValidateException {
        String resMes = "All ok";
        Pattern pattern = Pattern.compile("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$");
        Matcher matcher = pattern.matcher(eMail);

        if (eMail.length() > 20) {
            resMes = "eMail is too long";
        }else if (!eMail.isEmpty() && !matcher.matches()){
            resMes = "Incorrect email";
        }
        return resMes;
    }

    public static String additInfoValidate(String additInfo) throws ValidateException {
        String resMes = "All ok";

        if (additInfo.length() > 150) {
            resMes = "Additional info is too long";
        }
        return resMes;
    }

    public static void userFieldValidate(Map<String, String> param) throws ValidateException, DAOException {
        StringBuilder resMes = new StringBuilder();
        for (String key : param.keySet()) {
            String methodRes = "All ok";
            switch (key) {
                case ("id"):
                    methodRes = idValidate(param.get(key));
                    break;
                case ("name"):
                    methodRes = nameValidate(param.get(key));
                    break;
                case ("surname"):
                    methodRes = surnameValidate(param.get(key));
                    break;
                case ("status"):
                    methodRes = statusValidate(param.get(key));
                    break;
                case ("eMail"):
                    methodRes = eMailValidate(param.get(key));
                    break;
                case ("additionalInfo"):
                    methodRes = additInfoValidate(param.get(key));
                    break;
                case ("role"):
                    methodRes = roleValidate(param.get(key));
                    break;
                case ("password"):
                    methodRes = passwordValidate(param.get(key));
                    break;
                case ("login"):
                    if (!param.get(key).equals(param.get("prevUserLogin"))) {
                        methodRes = loginValidate(param.get(key));
                    }
                    break;
                case ("phoneNumber"):
                    methodRes = phoneValidate(param.get(key));
                    break;
                case ("prevUserLogin"):
                    break;
                default:
                    methodRes = "Incorrect parameter " + key;
                    break;
            }

            if (!"All ok".equals(methodRes)) {
                resMes.append(methodRes);
                resMes.append(" , ");
            }
        }

        if (!resMes.toString().equals("")) {
            String res = resMes.substring(0, resMes.length() - 3);
            throw new ValidateException(res);
        }
    }
}
