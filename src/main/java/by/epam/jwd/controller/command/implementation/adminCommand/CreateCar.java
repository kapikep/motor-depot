package by.epam.jwd.controller.command.implementation.adminCommand;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.controller.command.implementation.customerCommand.GoToCustomerEditOrder;
import by.epam.jwd.controller.constant.CommandName;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.implementation.CarServiceImpl;
import by.epam.jwd.service.interf.CarService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateCar implements Command {

    private final Logger log = LogManager.getLogger(CreateCar.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CarService carService = new CarServiceImpl();
        try {
            carService.createCar(request.getParameter("licencePlate"), request.getParameter("color"), "photo",
                    request.getParameter("odometr"), request.getParameter("status"), request.getParameter("carModel"));
        } catch (ServiceException e) {
            log.error("Catching: ", e);
        }
        response.sendRedirect(CommandName.ADMIN_COMMAND + CommandName.GO_TO_ADMIN_CARS_PAGE + "&message=Create done!");
    }
}
