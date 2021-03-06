package by.epam.jwd.controller.command.implementation.adminCommand;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.controller.constant.PagePath;
import by.epam.jwd.entity.*;
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
import java.sql.Timestamp;
import java.util.*;

public class GoToEditOrder implements Command {

    private final Logger log = LogManager.getLogger(GoToEditCar.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderService orderService = MDServiceFactory.getMDService().getOrderService();
        UserService userService = MDServiceFactory.getMDService().getUserService();
        CarService carService = MDServiceFactory.getMDService().getCarService();
        String editId = request.getParameter("editId");
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("wrongEnteredOrder");
        List<String> carTypes = null;
        List<Car> cars = new ArrayList<>();
        List<User> users = new ArrayList<>();

        try {
            if (order == null) {
                //edit order
                if (editId != null && !("".equals(editId)) && !("null".equals(editId))) {
                    order = orderService.readOrder(editId);
                    cars.add(carService.readCar(order.getCarId()));

                    if (order.getClientId() != 0) {
                        users.add(userService.readUser(order.getClientId()));
                    } else {
                        users.add(userService.readUser(1));
                    }
                } else {
                    //create Order
                    order = new Order();
                    order.setRequestDate(new Timestamp(new Date().getTime()));
                    users.add(userService.readUser(1));
                }

                if (order.getAdminId() == 0) {
                    order.setAdminId((int) session.getAttribute("userId"));
                    order.setAdminName((String) session.getAttribute("userFullName"));
                }
                carTypes = carService.readCarTypes();
                session.setAttribute("carTypes", carTypes);
                session.setAttribute("users", users);
                session.setAttribute("drivers", null);
                session.setAttribute("cars", cars);
            }
        } catch (ServiceException e) {
            log.error("Catching: ", e);
        }

        session.setAttribute("wrongEnteredOrder", null);
        request.setAttribute("order", order);

        request.getRequestDispatcher(PagePath.ADMIN_EDIT_ORDER_PAGE).forward(request, response);
    }
}
