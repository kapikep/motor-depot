package by.epam.jwd.controller.command.implementation.adminCommand;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.controller.command.implementation.customerCommand.GoToCustomerEditOrder;
import by.epam.jwd.controller.constant.CommandName;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.MDServiceFactory;
import by.epam.jwd.service.interf.CarService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateCarModel implements Command {

    private final Logger log = LogManager.getLogger(CreateCarModel.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CarService carService = MDServiceFactory.getMDService().getCarService();
        try {
            carService.createModel(request.getParameter("modelName"), request.getParameter("type"), request.getParameter("loadCapacity"),
                    request.getParameter("passengerCapacity"), request.getParameter("wheelDriveType"));
        } catch (ServiceException e) {
            log.error("Catching: ", e);
        }
        response.sendRedirect(CommandName.ADMIN_COMMAND + CommandName.ADMIN_GO_TO_EDIT_CAR);
    }
}
