package by.epam.jwd.controller.command.implementation;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.controller.command.implementation.adminCommand.EditOrder;
import by.epam.jwd.controller.constant.CommandName;
import by.epam.jwd.service.MDServiceFactory;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.ValidateException;
import by.epam.jwd.service.interf.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CreateOrder implements Command {

    private final Logger log = LogManager.getLogger(CreateOrder.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderService orderService = MDServiceFactory.getMDService().getOrderService();
        Map<String, String> param = new HashMap<>();
        String message;

        int countRequest = 0;
        if (request.getSession().getAttribute("countRequest") != null) {
            countRequest = (int) request.getSession().getAttribute("countRequest");
        }
        request.getSession().setAttribute("countRequest", ++countRequest);
        if (countRequest < 2) {
            try {
                param.put("criteria", request.getParameter("criteria"));
                param.put("clientPhone", request.getParameter("clientPhone"));
                param.put("contactDetails", request.getParameter("contactDetails"));

                orderService.createNotApproveOrder(param);
                message = "The order has been created, wait for the administrator to call";
            } catch (ServiceException e) {
                message = "Something wrong, try agan";
                log.error("Catching: ", e);
            } catch (ValidateException e) {
                message = "Wrong input, try agan";
                log.error("Catching: ", e);
            }
        } else {
            message = "To much requests";
        }
        response.sendRedirect(CommandName.WELCOME_COMMAND + CommandName.GO_TO_MAIN_PAGE + "&message=" + message);
    }
}
