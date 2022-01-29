package by.epam.jwd.controller.command.implementation.adminCommand;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.controller.constant.PagePath;
import by.epam.jwd.entity.Car;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.MDServiceFactory;
import by.epam.jwd.service.interf.CarService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoToManageCars implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CarService carService = MDServiceFactory.getMDService().getCarService();
        String rowLimit = (String) request.getSession().getAttribute("rowLimit");
        String page = (String) request.getAttribute("page");
        int pageCount = 1;
        List<Car> cars = null;
        List<Integer> numPages = null;
        String licensePlate = request.getParameter("license_plate");

        if(licensePlate == null){
            try {
                cars = carService.readCars(page, rowLimit);
                numPages = carService.pagination(page, rowLimit);
                pageCount = carService.getCarPageCount(rowLimit);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }else {
            try {
                cars = carService.findCars("licence_plate", licensePlate);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }

        request.setAttribute("cars", cars);
        request.setAttribute("pageCount", pageCount);
        request.setAttribute("numPages", numPages);
        request.getRequestDispatcher(PagePath.ADMIN_CARS_PAGE).forward(request, response);
    }
}
