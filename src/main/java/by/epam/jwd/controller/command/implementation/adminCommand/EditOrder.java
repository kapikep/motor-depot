package by.epam.jwd.controller.command.implementation.adminCommand;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.controller.constant.CommandName;
import by.epam.jwd.entity.Order;
import by.epam.jwd.service.MDServiceFactory;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.ValidateException;
import by.epam.jwd.service.interf.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class EditOrder implements Command {

    private final Logger log = LogManager.getLogger(EditOrder.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResourceBundle bundle = (ResourceBundle) request.getSession().getAttribute("bundle");
        OrderService orderService = MDServiceFactory.getMDService().getOrderService();
        Map<String, String> param = new HashMap<>();
        HttpSession session = request.getSession();
        String resMessage = null;
        boolean exception = false;
        String flag = request.getParameter("flag");

        Order order = null;

        param.put("id", request.getParameter("editId"));
        param.put("criteria", request.getParameter("criteria"));
        param.put("departPlace", request.getParameter("departPlace"));
        param.put("arrivalPlace", request.getParameter("arrivalPlace"));
        param.put("requestDate", request.getParameter("requestDate"));
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
        param.put("adminId", session.getAttribute("userId").toString());

        try {
            if ("create".equals(flag)) {
                orderService.createOrder(param);
                resMessage = bundle.getString("message.createDone");
            }

            if ("update".equals(flag)) {
                orderService.updateOrder(param);
                session.setAttribute("enteredOrder", null);
                resMessage = bundle.getString("message.updateDone");
            }
        } catch (ServiceException e) {
            exception = true;
            resMessage = bundle.getString("message.somethingWrong");
            log.error("Catching: ", e);
        } catch (ValidateException e) {
            exception = true;
            resMessage = e.getLocalizedMessage();
        }

        if (exception) {
            try {
                order = orderService.createOrderEntity(param);
            } catch (ServiceException e) {
                e.printStackTrace();
            }

            session.setAttribute("enteredOrder", order);

            response.sendRedirect(CommandName.ADMIN_COMMAND + CommandName.GO_TO_EDIT_USER + "&message=" +
                    URLEncoder.encode(resMessage, "UTF-8") + "&edit_id=" + request.getParameter("edit_id") + "&flag=" + flag);
        } else {
            response.sendRedirect(CommandName.ADMIN_COMMAND + CommandName.GO_TO_MAIN_ADMIN_PAGE + "&message=" + URLEncoder.encode(resMessage, "UTF-8"));
        }
    }
}
