package by.epam.jwd.service.validator;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.service.ValidateException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderValidator {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

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

    public static String criteriaValidate(String criteria) throws ValidateException {
        String resMes = "All ok";

        if (criteria.length() > 500) {
            resMes = "criteriaLong";
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

    public static String startDateValidate(String date){
        String resMes = "All ok";
        try{
            sdf.parse(date);
        }catch (ParseException e){
            resMes = "incorrectStartDate";
        }
        return resMes;
    }

    public static String endDateValidate(String endDateStr, String emplDateStr){
        String resMes = "All ok";
        Date endDate = null;
        Date startDate = null;

        try {
            startDate = sdf.parse(emplDateStr);
        } catch (ParseException e) {
            resMes = "incorrectStartDate";
        }

        if(endDateStr != null && !"".equals(endDateStr)) {
            try {
                endDate = sdf.parse(endDateStr);
                if (endDate.before(startDate)) {
                    resMes = "incorrectEndDateBec";
                }
            } catch (ParseException e) {
                resMes = "incorrectEndDate";
            }
        }
        return resMes;
    }

    public static void orderFieldValueValidate(Map<String, String> param) throws ValidateException, DAOException {
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
                case ("clientId"):
                case ("adminId"):
                case ("carId"):
                    methodRes = idValidate(param.get(key));
                    break;
                case ("criteria"):
                    methodRes = criteriaValidate(param.get(key));
                    break;
                case ("status"):
                    methodRes = UserValidator.statusValidate(param.get(key));
                    break;
                case ("clientFullName"):
                    methodRes = nameValidate(param.get(key));
                    break;
                case ("phoneNumber"):
                    methodRes = UserValidator.phoneValidate(param.get(key));
                    break;
                case ("adminName"):
                    methodRes = nameValidate(param.get(key));
                    break;
                case ("startDate"):
                    methodRes = startDateValidate(param.get(key));
                    break;
                case ("endDate"):
                    methodRes = endDateValidate(param.get(key), param.get("startDate"));
                    break;
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

