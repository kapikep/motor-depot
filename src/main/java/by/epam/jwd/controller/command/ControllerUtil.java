package by.epam.jwd.controller.command;

import by.epam.jwd.service.ServiceUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerUtil {

    public static void pagination(HttpServletRequest request, HttpServletResponse response) {
        String rowLimit = request.getParameter("rowLimit");
        String page =  request.getParameter("page");

        if(rowLimit != null){
            request.getSession().setAttribute("rowLimit", rowLimit);
        }

        request.setAttribute("page", page);
    }
}
