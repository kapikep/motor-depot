package by.epam.jwd.controller.command.implementation.adminCommand;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.controller.command.implementation.customerCommand.GoToCustomerEditOrder;
import by.epam.jwd.controller.command.implementation.driverCommand.UpdateOrderByDriver;
import by.epam.jwd.controller.constant.PagePath;
import by.epam.jwd.entity.Car;
import by.epam.jwd.entity.Driver;
import by.epam.jwd.entity.User;
import by.epam.jwd.service.MDServiceFactory;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.interf.CarService;
import by.epam.jwd.service.interf.DriverService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GoToEditDriver implements Command {

    private final Logger log = LogManager.getLogger(GoToEditDriver.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DriverService driverService = MDServiceFactory.getMDService().getDriverService();
        CarService carService = MDServiceFactory.getMDService().getCarService();
        String editId = request.getParameter("edit_id");
        HttpSession session = request.getSession();
        List<Car> cars = null;
        Driver driver = (Driver) session.getAttribute("wrongDriver");

        try {
            if (driver == null && editId != null && !("".equals(editId))) {
                driver = driverService.readDriver(editId);
                session.setAttribute("editUserLogin", driver.getLogin());
            }
            cars =  carService.readCars();
        } catch (ServiceException e) {
            log.error("Catching: ", e);
        }

        session.setAttribute("wrongDriver", null);
        request.setAttribute("driver", driver);
        request.setAttribute("cars", cars);
        request.getRequestDispatcher(PagePath.ADMIN_EDIT_DRIVER_PAGE).forward(request, response);
    }
}
