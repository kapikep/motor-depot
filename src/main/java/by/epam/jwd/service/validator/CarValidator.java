package by.epam.jwd.service.validator;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.service.ValidateException;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CarValidator {


/*
    public static void carFieldValidate(Map<String, String> param) throws ValidateException, DAOException {
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
*/

}
