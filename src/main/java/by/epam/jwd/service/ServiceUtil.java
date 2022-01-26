package by.epam.jwd.service;

import java.util.ArrayList;
import java.util.List;

public class ServiceUtil {
    public static List<Integer> pagination(int pageCount, int rowLimit, int page) {

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
        if (str != null) {
            i = Integer.parseInt(str);
        }
        return i;
    }

    public static int parseInt(String str) throws ServiceException {
        int i = 0;
        if (str != null) {
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

    public static String checkRowLimit(String paramRowLimit) {
        String rowLimit = "10";

        if (paramRowLimit != null) {
            rowLimit = paramRowLimit;
        }
        return rowLimit;
    }

    public static String checkPage(String paramPage) {
        String page = "1";

        if (paramPage != null) {
            page = paramPage;
        }
        return page;
    }
}
