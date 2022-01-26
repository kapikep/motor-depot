package by.epam.jwd.controller.command;

import java.util.ArrayList;
import java.util.List;

public class Pagination {

    public static List<Integer> pagination(int page, int pageCount){
        List<Integer> res = new ArrayList<>();

        for (int i = page - 3; i <= page; i++) {
            if(i >= 1){
                res.add(i);
            }
        }

        for (int i = page + 1; i <= page + 3; i++) {
            if(i <= pageCount){
                res.add(i);
            }
        }

        return res;
    }
}
