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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class GoToEditOrder implements Command {

    private final Logger log = LogManager.getLogger(GoToEditCar.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderService orderService = MDServiceFactory.getMDService().getOrderService();
        UserService userService = MDServiceFactory.getMDService().getUserService();
        CarService carService = MDServiceFactory.getMDService().getCarService();
        String edit_id = request.getParameter("edit_id");
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("enteredOrder");
        String adminName = (String) session.getAttribute("userFullName");
        List<String> carTypes = null;
        List<Car> cars = new ArrayList<>();
        List<User> users = new ArrayList<>();

        try {
            if(order == null && edit_id != null && !("".equals(edit_id))) {
                order = orderService.readOrder(edit_id);
                cars.add(carService.readCar(order.getCarId()));

                if(order.getClientId() != 0){
                    users.add(userService.readUser(order.getClientId()));
                }

                if(order.getAdminName() == null){
                    order.setAdminName(adminName);
                }
            }else {
                order = new Order();
                order.setStartDate(new Timestamp(new Date().getTime()));
                order.setEndDate(new Timestamp(new Date().getTime()));
                order.setRequestDate(new Timestamp(new Date().getTime()));
                order.setAdminName(adminName);
            }
            carTypes = carService.readCarTypes();
        } catch (ServiceException e) {
            log.error("Catching: ", e);
        }

        if(users.isEmpty()){
            users.add(new User());
        }

        session.setAttribute("users", users);
        session.setAttribute("cars", cars);
        session.setAttribute("carTypes", carTypes);
        request.setAttribute("order", order);

        request.getRequestDispatcher(PagePath.ADMIN_EDIT_ORDER_PAGE).forward(request, response);
    }
}
