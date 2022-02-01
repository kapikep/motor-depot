package by.epam.jwd.controller.command.implementation.adminCommand;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.controller.command.implementation.customerCommand.GoToCustomerEditOrder;
import by.epam.jwd.controller.constant.CommandName;
import by.epam.jwd.entity.Order;
import by.epam.jwd.service.MDServiceFactory;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.interf.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UpdateOrder implements Command {

    private final Logger log = LogManager.getLogger(UpdateOrder.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderService orderService = MDServiceFactory.getMDService().getOrderService();
        String message = "Update done";

        Map<String, String> param = new HashMap<>();
        param.put("editId", request.getParameter("edit_id"));
        param.put("criteria", request.getParameter("criteria"));
        param.put("departPlace", request.getParameter("departPlace"));
        param.put("arrivalPlace", request.getParameter("arrivalPlace"));
        param.put("startDate", request.getParameter("startDate"));
        param.put("endDate", request.getParameter("endDate"));
        param.put("distance", request.getParameter("distance"));
        param.put("totalAmount", request.getParameter("totalAmount"));
        param.put("paymentStatus", request.getParameter("paymentStatus"));
        param.put("status", request.getParameter("status"));
        param.put("clientFullName", request.getParameter("clientFullName"));
        param.put("clientPhone", request.getParameter("clientPhone"));
        param.put("adminName", request.getParameter("adminName"));
        param.put("carId", request.getParameter("car"));
        param.put("adminId", request.getSession().getAttribute("userId").toString());

        try {
            orderService.updateOrder(param);
        } catch (ServiceException e) {
            message = "Something wrong";
            log.error("Catching: ", e);
        }

        response.sendRedirect(CommandName.ADMIN_COMMAND + CommandName.GO_TO_MAIN_ADMIN_PAGE + "&message=" + message);
    }
}
