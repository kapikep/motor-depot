package by.epam.jwd.service.validator;

import by.epam.jwd.dao.DAOException;
import by.epam.jwd.service.ValidateException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DriverValidator {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

    public static String categoryValidate(String category) {
        String resMes = "All ok";

        if (category.length() > 20) {
            resMes = "categoryLong";
        }
        return resMes;
    }

    public static String drivingExperienceValidate(String drEx) {
        String resMes = "All ok";
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(drEx);

        if (!(matcher.matches())) {
            resMes = "incorrectDrivingExp";
        } else if (drEx.length() > 20) {
            resMes = "drivingExpLong";
        }
        return resMes;
    }

    public static String dateOfEmploymentValidate(String date){
        String resMes = "All ok";
        try{
            sdf.parse(date);
        }catch (ParseException  e){
            resMes = "incorrectDateOfEmployment";
        }
        return resMes;
    }

    public static String dateOfDismissalValidate(String dismDateStr, String emplDateStr){
        String resMes = "All ok";
        Date dismDate = null;
        Date emplDate = null;

        try {
            emplDate = sdf.parse(emplDateStr);
        } catch (ParseException e) {
            resMes = "incorrectDateOfEmployment";
        }

        if(dismDateStr != null && !"".equals(dismDateStr)) {
            try {
                dismDate = sdf.parse(dismDateStr);
                if (dismDate.before(emplDate)) {
                    resMes = "incorrectDateOfDismBefEmpl";
                }
            } catch (ParseException e) {
                resMes = "incorrectDateOfDism";
            }
        }

        return resMes;
    }

    public static void driverFieldValueValidate(Map<String, String> param) throws ValidateException, DAOException {
        StringBuilder resMes = new StringBuilder();
        StringBuilder locResMes = new StringBuilder();
        Locale locale;

        if (param.get("locale") != null) {
            locale = new Locale(param.get("locale"));
        } else {
            locale = new Locale("en");
        }

        for (String key : param.keySet()) {
            String methodRes = "All ok";
            switch (key) {
                case ("category"):
                    methodRes = categoryValidate(param.get(key));
                    break;
                case ("drivingExperience"):
                    methodRes = drivingExperienceValidate(param.get(key));
                    break;
                case ("dateOfEmployment"):
                    methodRes = dateOfEmploymentValidate(param.get(key));
                    break;
                case ("dateOfDismissal"):
                    methodRes = dateOfDismissalValidate(param.get(key), param.get("dateOfEmployment"));
                    break;
            }

            if (!"All ok".equals(methodRes)) {
                ResourceBundle resourceBundle = ResourceBundle.getBundle("localization.validatorMessages", locale);
                if (resourceBundle.containsKey(methodRes)) {
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
            if (!locResMes.toString().equals("")) {
                locRes = locResMes.substring(0, locResMes.length() - 3);
            }
            throw new ValidateException(res, locRes);
        }
    }

}
