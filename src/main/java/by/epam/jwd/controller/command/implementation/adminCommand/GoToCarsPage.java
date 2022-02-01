package by.epam.jwd.controller.command.implementation.adminCommand;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.controller.constant.PagePath;
import by.epam.jwd.entity.Car;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.MDServiceFactory;
import by.epam.jwd.service.interf.CarService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoToCarsPage implements Command {

    private final Logger log = LogManager.getLogger(GoToCarsPage.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageCount = 1;
        List<Car> cars = null;
        List<Integer> numPages = null;
        String page = request.getParameter("page");
        String rowLimit = request.getParameter("rowLimit");
        String licensePlate = request.getParameter("license_plate");
        CarService carService = MDServiceFactory.getMDService().getCarService();

        if (rowLimit != null && !("".equals(rowLimit))) {
            request.getSession().setAttribute("rowLimit", rowLimit);
        }else {
            rowLimit = (String) request.getSession().getAttribute("rowLimit");
        }

        if (page == null || "".equals(page)) {
            page = "1";
        }

        if (licensePlate == null) {
            try {
                cars = carService.readCars(page, rowLimit);
                numPages = carService.pagination(page, rowLimit);
                pageCount = carService.getCarPageCount(rowLimit);
            } catch (ServiceException e) {
                log.error("Catching: ", e);
            }
        } else {
            try {
                cars = carService.findCars("licence_plate", licensePlate);
            } catch (ServiceException e) {
                log.error("Catching: ", e);
            }
        }

        request.setAttribute("page", page);
        request.setAttribute("cars", cars);
        request.setAttribute("pageCount", pageCount);
        request.setAttribute("numPages", numPages);
        request.getRequestDispatcher(PagePath.ADMIN_CARS_PAGE).forward(request, response);
    }
}
