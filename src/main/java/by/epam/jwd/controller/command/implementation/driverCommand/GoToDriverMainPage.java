package by.epam.jwd.controller.command.implementation.driverCommand;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.controller.constant.PagePath;
import by.epam.jwd.entity.Order;
import by.epam.jwd.entity.Status;
import by.epam.jwd.service.MDServiceFactory;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.interf.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class GoToDriverMainPage implements Command {

    private final Logger log = LogManager.getLogger(GoToDriverMainPage.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orders = null;
        Map<String, String> map = new HashMap<>();
        OrderService orderService = MDServiceFactory.getMDService().getOrderService();
        int userId = (int) request.getSession().getAttribute("userId");

        try {
            map.put("driver_id", Integer.toString(userId));
            map.put("start_date>", new Date().toString());
            System.out.println(new Date());
            orders = orderService.readOrders(map);
        } catch (ServiceException e) {
            log.error("Catching: ", e);
        }

        request.setAttribute("orders", orders);
        request.getRequestDispatcher(PagePath.MAIN_DRIVER_PAGE).forward(request, response);
    }
}

