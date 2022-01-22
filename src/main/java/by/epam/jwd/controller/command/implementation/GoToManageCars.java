package by.epam.jwd.controller.command.implementation;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.entity.Car;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.implementation.CarServiceImpl;
import by.epam.jwd.service.interf.CarService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoToManageCars implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Car> cars = null;
        CarService carService = new CarServiceImpl();
        try {
            cars = carService.readAllCar();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        request.setAttribute("cars", cars);
        System.out.println(cars);
        request.getRequestDispatcher(ADMIN_CARS_PAGE).forward(request, response);
    }
}
