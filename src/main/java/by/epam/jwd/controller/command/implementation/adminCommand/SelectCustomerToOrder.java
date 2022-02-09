package by.epam.jwd.controller.command.implementation.adminCommand;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.controller.constant.PagePath;
import by.epam.jwd.entity.Order;
import by.epam.jwd.entity.Role;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectCustomerToOrder implements Command {

    private final Logger log = LogManager.getLogger(SelectCustomerToOrder.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderService orderService = MDServiceFactory.getMDService().getOrderService();
        UserService userService = MDServiceFactory.getMDService().getUserService();
        String searchName = request.getParameter("searchName");
        String searchSurname = request.getParameter("searchSurname");
        Map<String, String> userParam = new HashMap<>();
        Map<String, String> param = new HashMap<>();
        HttpSession session = request.getSession();
        Order order = null;
        List<User> users = new ArrayList<>();

        EditOrder.fillingOrderParamMap(request, param);

        if (searchName != null && !"".equals(searchName)) {
            userParam.put("name", searchName);
        }

        if (searchSurname != null && !"".equals(searchSurname)) {
            userParam.put("surname", searchSurname);
        }

        if (!userParam.isEmpty()) {
            try {
                userParam.put("roles_id", Integer.toString(Role.CUSTOMER.getId()));
                users = userService.findUsers(userParam);
            } catch (ServiceException e) {
                log.error("Catching: ", e);
            }
        }

        try {
            order = orderService.createOrderEntity(param);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        if (users.isEmpty()) {
            users.add(new User());
        }

        session.setAttribute("users", users);
        request.setAttribute("order", order);

        request.getRequestDispatcher(PagePath.ADMIN_EDIT_ORDER_PAGE).forward(request, response);
    }
}
