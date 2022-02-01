package by.epam.jwd.controller.command.implementation.adminCommand;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.controller.constant.PagePath;
import by.epam.jwd.entity.Car;
import by.epam.jwd.entity.CarModel;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.MDServiceFactory;
import by.epam.jwd.service.interf.CarService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoToEditCar implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CarService carService = MDServiceFactory.getMDService().getCarService();
        List<CarModel> carModels = null;
        Car car = null;
        String edit_id = request.getParameter("edit_id");
        try {
            if(edit_id != null) {
                car = carService.readCar(edit_id);
                request.setAttribute("edit", true);
            }else {
                request.setAttribute("create", true);
            }
            request.setAttribute("car", car);
            carModels = carService.readCarModels();
            request.setAttribute("carModels", carModels);
        } catch (ServiceException e) {
            //TODO logger
            e.printStackTrace();
        }
        request.getRequestDispatcher(PagePath.ADMIN_EDIT_CAR_PAGE).forward(request, response);
    }
}