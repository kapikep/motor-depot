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
        String adminName = (String) request.getSession().getAttribute("userFullName");
        String edit_id = request.getParameter("edit_id");
        try {
            carTypes = carService.readCarTypes();
            if(edit_id != null && !("".equals(edit_id))) {
                order = orderService.readOrder(edit_id);
                car = carService.readCar(Integer.toString(order.getCarId()));

                if(order.getAdminName() == null){
                    order.setAdminName(adminName);
                }

                request.setAttribute("edit", true);
                request.setAttribute("step1", true);
            }else {
                request.setAttribute("create", true);
                request.setAttribute("step1", true);
                order = new Order();
                order.setRequestDate(new Date());
                order.setAdminName(adminName);
            }

            request.setAttribute("carTypes", carTypes);
            request.setAttribute("order", order);
            request.setAttribute("car", car);
        } catch (ServiceException e) {
            //TODO logger
            e.printStackTrace();
        }
        request.getRequestDispatcher(PagePath.ADMIN_EDIT_ORDER_PAGE).forward(request, response);
    }
}
