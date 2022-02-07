package by.epam.jwd.controller.command.implementation.adminCommand;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.controller.command.implementation.driverCommand.UpdateOrderByDriver;
import by.epam.jwd.controller.constant.PagePath;
import by.epam.jwd.entity.Car;
import by.epam.jwd.entity.CarModel;
import by.epam.jwd.entity.Order;
import by.epam.jwd.entity.User;
import by.epam.jwd.service.MDServiceFactory;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.interf.CarService;
import by.epam.jwd.service.interf.OrderService;
import by.epam.jwd.service.interf.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class GoToEditOrder implements Command {

    private final Logger log = LogManager.getLogger(GoToEditCar.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderService orderService = MDServiceFactory.getMDService().getOrderService();
        CarService carService = MDServiceFactory.getMDService().getCarService();
        UserService userService = MDServiceFactory.getMDService().getUserService();
        String edit_id = request.getParameter("edit_id");
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("wrongOrder");
        String adminName = (String) session.getAttribute("userFullName");
        User user = null;
        List<Order> orders = null;
        List<CarModel> carModels = null;
        List<String> carTypes = null;
        Car car = null;
        try {
            if(order == null && edit_id != null && !("".equals(edit_id))) {
                order = orderService.readOrder(edit_id);
                car = carService.readCar(Integer.toString(order.getCarId()));

                if(order.getClientId() != 0){
                    user = userService.readUser(order.getClientId());
                }

                if(order.getAdminName() == null){
                    order.setAdminName(adminName);
                }
            }else {
                order = new Order();
                order.setRequestDate(new Date());
                order.setAdminName(adminName);
            }

            carTypes = carService.readCarTypes();
            request.setAttribute("step", "1");
            request.setAttribute("carTypes", carTypes);
            request.setAttribute("user", user);
            request.setAttribute("order", order);
            request.setAttribute("car", car);
        } catch (ServiceException e) {
            log.error("Catching: ", e);
        }
        request.getRequestDispatcher(PagePath.ADMIN_EDIT_ORDER_PAGE).forward(request, response);
    }
}
