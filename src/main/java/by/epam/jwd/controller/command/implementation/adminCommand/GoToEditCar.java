package by.epam.jwd.controller.command.implementation.adminCommand;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.entity.CarModel;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.implementation.CarServiceImpl;
import by.epam.jwd.service.interf.CarService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoToEditCar implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CarService carService = new CarServiceImpl();
        List<CarModel> carModels = null;
        int edit_id;


        if(request.getParameter("edit_id") != null) {
            edit_id = Integer.parseInt(request.getParameter("edit_id"));

        }
        try {
            carModels = carService.readAllCarModel();
        } catch (ServiceException e) {
            //TODO logger
            e.printStackTrace();
        }
        request.setAttribute("carModels", carModels);
        request.getRequestDispatcher(ADMIN_CREATE_CAR_PAGE).forward(request, response);
    }
}