package by.epam.jwd.controller.command.implementation.adminCommand;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.entity.Car;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.implementation.CarServiceImpl;
import by.epam.jwd.service.interf.CarService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoToManageCars implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageCount = 0;
        int rowLimit = 5;
        int page = 1;
        String rowLimitStr;
        String pageStr;
        List<Car> cars = null;
        CarService carService = new CarServiceImpl();
        rowLimitStr = request.getParameter("rowLimit");


        if(rowLimitStr != null){
            rowLimit = Integer.parseInt(rowLimitStr);
            request.getSession().setAttribute("rowLimit", rowLimitStr);
        }else {
            rowLimitStr = (String) request.getSession().getAttribute("rowLimit");
        }

        if(rowLimitStr != null) {
            rowLimit = Integer.parseInt(rowLimitStr);
        }

        pageStr = request.getParameter("page");

        if (pageStr != null){
            page = Integer.parseInt(pageStr);
        }
        try {
            pageCount = carService.getCarPageCount(rowLimit);
            cars = carService.readCarsWithOffset(page, rowLimit);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        request.setAttribute("pageCount", pageCount);
        request.setAttribute("cars", cars);
        request.getRequestDispatcher(ADMIN_CARS_PAGE).forward(request, response);
    }
}
