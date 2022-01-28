package by.epam.jwd.controller.command.implementation.adminCommand;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.controller.constant.CommandName;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.MDServiceFactory;
import by.epam.jwd.service.interf.CarService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateCar implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CarService carService = MDServiceFactory.getMDService().getCarService();
        try {
            carService.updateCar(request.getParameter("id"), request.getParameter("licencePlate"), request.getParameter("color"), "photo",
                    request.getParameter("odometr"), request.getParameter("status"), request.getParameter("carModel"));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        response.sendRedirect(CommandName.ADMIN_COMMAND + CommandName.GO_TO_ADMIN_CARS_PAGE + "&message=Update done!");
    }
}
