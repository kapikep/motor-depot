package by.epam.jwd.controller.command.implementation.adminCommand;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.controller.constant.CommandName;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.implementation.CarServiceImpl;
import by.epam.jwd.service.implementation.MDServiceFactoryImpl;
import by.epam.jwd.service.interf.CarService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCar implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CarService carService = MDServiceFactoryImpl.getMDService().getCarService();
        try {
            carService.deleteCar(request.getParameter("id"));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        //response.sendRedirect(DEFAULT_ADMIN_PATH + COMMAND + GO_TO_ADMIN_CARS_PAGE + "&message=Delete done!");
        response.sendRedirect(CommandName.ADMIN_COMMAND + CommandName.GO_TO_ADMIN_CARS_PAGE + "&message=Delete done!");
    }
}
