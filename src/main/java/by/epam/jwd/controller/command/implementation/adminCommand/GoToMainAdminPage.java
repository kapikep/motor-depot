package by.epam.jwd.controller.command.implementation.adminCommand;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.controller.command.implementation.customerCommand.GoToCustomerEditOrder;
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
import java.util.List;

public class GoToMainAdminPage implements Command {

    private final Logger log = LogManager.getLogger(GoToMainAdminPage.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderService orderService = MDServiceFactory.getMDService().getOrderService();

        List<Order> orders = null;

        try {
            orders = orderService.findOrders( "order_status", Status.NOT_APPROVE.toString());
        } catch (ServiceException e) {
            log.error("Catching: ", e);
        }

        request.setAttribute("orders", orders);
        request.getRequestDispatcher(PagePath.MAIN_ADMIN_PAGE).forward(request, response);
    }
}

