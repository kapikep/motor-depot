package by.epam.jwd.controller.command.implementation.adminCommand;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.entity.Car;
import by.epam.jwd.entity.CarModel;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.implementation.CarServiceImpl;
import by.epam.jwd.service.interf.CarService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GoToCreateCar implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //int id = Integer.parseInt(request.getParameter("id"));

        CarService carService = new CarServiceImpl();
        List<CarModel> carModels = null;
        try {
            carModels = carService.readAllCarModel();
        } catch (ServiceException e) {
            //TODO logger
            e.printStackTrace();
        }
        HttpSession session = request.getSession();
        session.setAttribute("carModels", carModels);
        request.getRequestDispatcher(ADMIN_CREATE_CAR_PAGE).forward(request, response);
    }
}
