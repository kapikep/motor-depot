package by.epam.jwd.controller.command.implementation.adminCommand;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.controller.constant.PagePath;
import by.epam.jwd.entity.Car;
import by.epam.jwd.entity.CarModel;
import by.epam.jwd.entity.Order;
import by.epam.jwd.service.MDServiceFactory;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.interf.CarService;
import by.epam.jwd.service.interf.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class GoToEditOrder implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderService orderService = MDServiceFactory.getMDService().getOrderService();
        CarService carService = MDServiceFactory.getMDService().getCarService();
        List<Order> orders = null;
        List<CarModel> carModels = null;
        List<String> carTypes = null;
        Car car = null;
        Order order = null;
        String edit_id = request.getParameter("edit_id");
        try {
            if(edit_id != null && !("".equals(edit_id))) {
                car = carService.readCar(edit_id);
                order = orderService.readOrder(edit_id);
                request.setAttribute("edit", true);
            }else {
                request.setAttribute("create", true);
                order = new Order();
                order.setRequestDate(new Date());
                order.setAdminName((String) request.getSession().getAttribute("userFullName"));
                carTypes = carService.readCarTypes();

                request.setAttribute("carTypes", carTypes);
            }
            request.setAttribute("order", order);
            request.setAttribute("car", car);
        } catch (ServiceException e) {
            //TODO logger
            e.printStackTrace();
        }
        request.getRequestDispatcher(PagePath.ADMIN_EDIT_ORDER_PAGE).forward(request, response);
    }
}
