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
        String resMessage = bundle.getString("message.somethingWrong");
        boolean exception = false;
        String flag = request.getParameter("flag");
        Order order = null;

        fillingOrderParamMap(request, param, session);

        try {
            if ("create".equals(flag)) {
                orderService.createOrder(param);
                session.setAttribute("wrongEnteredOrder", null);
                resMessage = bundle.getString("message.createDone");
            }
            if ("update".equals(flag)) {
                orderService.updateOrder(param);
                session.setAttribute("wrongEnteredOrder", null);
                resMessage = bundle.getString("message.updateDone");
            }
        } catch (ServiceException e) {
            exception = true;
            resMessage = bundle.getString("message.somethingWrong");
            log.error("Catching: ", e);
        } catch (ValidateException e) {
            exception = true;
            log.debug("Catching: ", e);
            System.out.println(e.getMessage());
            resMessage = e.getLocalizedMessage();
        }
        if (exception) {
            try {
                order = orderService.createOrderEntity(param);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
            if(resMessage == null){
                resMessage = bundle.getString("message.somethingWrong");
            }//TODO add to other
            session.setAttribute("wrongEnteredOrder", order);
            response.sendRedirect(CommandName.ADMIN_COMMAND + CommandName.GO_TO_ADMIN_EDIT_ORDER + "&message=" +
                    URLEncoder.encode(resMessage, "UTF-8") + "&editId=" + request.getParameter("editId") + "&flag=" + flag);
        } else {
            response.sendRedirect(CommandName.ADMIN_COMMAND + CommandName.GO_TO_MAIN_ADMIN_PAGE + "&message=" + URLEncoder.encode(resMessage, "UTF-8"));
        }
    }

    static void fillingOrderParamMap(HttpServletRequest request, Map<String, String> param, HttpSession session) {
        param.put("id", request.getParameter("editId"));
        param.put("criteria", request.getParameter("criteria"));
        param.put("requestDate", request.getParameter("requestDate"));
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
        param.put("adminSurname", request.getParameter("adminSurname"));
        param.put("driverId", request.getParameter("driverId"));
        param.put("adminId", request.getParameter("adminId"));
        param.put("clientId", request.getParameter("selectedUser"));
        param.put("carId", request.getParameter("selectedCar"));
    }
}
