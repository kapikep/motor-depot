package by.epam.jwd.service.validator;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.dao.MotorDepotDAOFactory;
import by.epam.jwd.dao.interf.UserDao;
import by.epam.jwd.entity.Role;
import by.epam.jwd.entity.Status;
import by.epam.jwd.entity.User;
import by.epam.jwd.service.ValidateException;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {

    private static final UserDao userDao = MotorDepotDAOFactory.getMotorDepotDAOFactory().getUserDao();
    private static final List<String> paramFields = Arrays.asList("id" , "name", "surname", "login", "password",
            "phoneNumber", "status", "eMail", "additionalInfo", "role", "locale");

    public static String idValidate(String id) throws ValidateException {
        String resMes = "All ok";
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(id);

        if(!(matcher.matches())){
            resMes = "Id not digit";
        } else if (id.length() > 20) {
            resMes = "Id is too long";
        }
        return resMes;
    }

    public static String loginValidate(String login) throws ValidateException, DAOException {
        List<User> users = null;
        String resMes = "All ok";
        if (login.isEmpty()) {
            resMes = "loginEmpty";
        } else if (login.length() > 20) {
            resMes = "loginLong";
        } else {
            users = userDao.findUsers("login", login);
            if (!users.isEmpty()) {
                resMes = "loginExists";
            }
        }
        return resMes;
    }

    public static String passwordValidate(String password) throws ValidateException {
        String resMes = "All ok";
        if (password.isEmpty()) {
            resMes = "passEmpty";
        } else if (password.length() > 20) {
            resMes = "passLong";
        } else if ((password.length() < 6)) {
            resMes = "passLess";
        }
        return resMes;
    }

    public static String phoneValidate(String phone) throws ValidateException {
        String resMes = "All ok";
        Pattern pattern = Pattern.compile("\\+?\\d{1,15}");
        Matcher matcher = pattern.matcher(phone);

        if (phone.isEmpty()) {
            resMes = "phoneEmpty";
        } else if (!matcher.matches()) {
            resMes = "incorrectPhone";
        }
        return resMes;
    }

    public static String nameValidate(String name) throws ValidateException {
        String resMes = "All ok";
        Pattern pattern = Pattern.compile("([A-Z]|[А-Я])([a-z]|[а-я])+");
        Matcher matcher = pattern.matcher(name);

        if (name.isEmpty()) {
            resMes = "nameEmpty";
        } else if (name.length() > 20) {
            resMes = "nameLong";
        } else if (!matcher.matches()){
            resMes = "incorrectName";
        }
        return resMes;
    }

    public static String surnameValidate(String surname) throws ValidateException {
        String resMes = "All ok";
        Pattern pattern = Pattern.compile("([A-Z]|[А-Я])([a-z]|[а-я])+");
        Matcher matcher = pattern.matcher(surname);

        if (surname.isEmpty()) {
            resMes = "surnameEmpty";
        } else if (surname.length() > 20) {
            resMes = "surnameLong";
        } else if (!matcher.matches()){
            resMes = "incorrectSurname";
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
            resMes = "eMailLong";
        }else if (!eMail.isEmpty() && !matcher.matches()){
            resMes = "incorrectEmail";
        }
        return resMes;
    }

    public static String additInfoValidate(String additInfo) throws ValidateException {
        String resMes = "All ok";

        if (additInfo.length() > 150) {
            resMes = "additInfoLong";
        }
        return resMes;
    }

    public static void userFieldValidate(Map<String, String> param) throws ValidateException {

        for (String key : param.keySet()) {
            if(!paramFields.contains(key)){
                throw new ValidateException(key + " - field not exist");
            }
        }
    }

    public static void userFieldValueValidate(Map<String, String> param) throws ValidateException, DAOException {
        StringBuilder resMes = new StringBuilder();
        StringBuilder locResMes = new StringBuilder();
        Locale locale;

        if (param.get("locale") != null){
            locale = new Locale(param.get("locale"));
        }else {
            locale = new Locale("en");
        }

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
                case ("locale"):
                    break;
//                default:
//                    methodRes = "Incorrect parameter " + key;
//                    break;
            }

            if (!"All ok".equals(methodRes)) {
                ResourceBundle resourceBundle = ResourceBundle.getBundle("localization.validatorMessages", locale);
                if(resourceBundle.containsKey(methodRes)){
                    locResMes.append(resourceBundle.getString(methodRes));
                    locResMes.append(" , ");
                }
                resMes.append(methodRes);
                resMes.append(" , ");
            }
        }

        if (!resMes.toString().equals("")) {
            String res = resMes.substring(0, resMes.length() - 3);
            String locRes = "";
            if(!locResMes.toString().equals("")){
                locRes = locResMes.substring(0, locResMes.length() - 3);
            }
            throw new ValidateException(res, locRes);
        }
    }
}
