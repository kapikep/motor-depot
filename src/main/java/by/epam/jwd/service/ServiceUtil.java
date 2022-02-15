package by.epam.jwd.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ServiceUtil {

    public static List<Integer> getPagesNumber(int pageCount, int rowLimit, int page) {
        List<Integer> res = new ArrayList<>();
        int rowWidth = 5;
        int row = page / rowWidth;
        int start = rowWidth * row;
        int border = start + rowWidth + 1;

        for (int i = start; i < border && i <= pageCount; i++) {
            if (i != 0) {
                res.add(i);
            }
        }
        return res;
    }

    public static int parseInt(String str, int defaultVal) {
        int i = defaultVal;
        if (str != null && Pattern.matches("\\d+", str)) {
            i = Integer.parseInt(str);
        }
        return i;
    }

    public static int parseInt(String str) throws ServiceException {
        int i = 0;
        if (str != null && Pattern.matches("\\d+", str)) {
            i = Integer.parseInt(str);
        } else {
            throw new ServiceException("Null parameter " + str);
        }
        return i;
    }

    public static double parseDouble(String str) throws ServiceException {
        double d = 0;
        if (str != null) {
            d = Double.parseDouble(str);
        } else {
            throw new ServiceException("Null parameter " + str);
        }
        return d;
    }
}
