package by.epam.jwd.controller.command.implementation.adminCommand;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.implementation.CarServiceImpl;
import by.epam.jwd.service.interf.CarService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;

public class CreateCarModel implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CarService carService = new CarServiceImpl();
        try {
            carService.createMadel(request.getParameter("modelName"), request.getParameter("type"), request.getParameter("loadCapacity"),
                    request.getParameter("passengerCapacity"), request.getParameter("wheelDriveType"));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        response.sendRedirect(DEFAULT_ADMIN_PATH + COMMAND + GO_TO_ADMIN_CARS_PAGE);
    }
}
