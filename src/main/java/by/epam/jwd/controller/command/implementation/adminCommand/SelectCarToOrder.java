package by.epam.jwd.controller.command.implementation.adminCommand;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.controller.command.implementation.customerCommand.GoToCustomerEditOrder;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SelectCarToOrder implements Command {

    private final Logger log = LogManager.getLogger(SelectCarToOrder.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderService orderService = MDServiceFactory.getMDService().getOrderService();
        CarService carService = MDServiceFactory.getMDService().getCarService();
        SimpleDateFormat sdfDateTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        SimpleDateFormat sdfTimestamp = new SimpleDateFormat("yy-MM-dd HH:mm:ss.SSS");
        Map<String, String> criteriaCarMap = new HashMap<>();
        Map<String, String> timeSearch = new HashMap<>();
        Map<String, String> param = new HashMap<>();
        HttpSession session = request.getSession();
        List<Car> cars = null;
        Order order = new Order();
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");

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
        param.put("adminName", (String) session.getAttribute("userFullName"));
        param.put("carId", request.getParameter("car"));
        param.put("adminId", request.getSession().getAttribute("userId").toString());

        try {
            criteriaCarMap.put("load_capacity", request.getParameter("loadCapacity"));
            criteriaCarMap.put("passenger_capacity", request.getParameter("passengerCapacity"));
            criteriaCarMap.put("status", "active");
            criteriaCarMap.put("type", request.getParameter("carType"));

            if (startDateStr != null && !("".equals(startDateStr))) {
                timeSearch.put("start_date>", sdfTimestamp.format(sdfDateTime.parse(startDateStr)));
            }

            if (endDateStr != null && !("".equals(endDateStr))) {
                timeSearch.put("end_date<", sdfTimestamp.format(sdfDateTime.parse(endDateStr)));
            }

            timeSearch.put("order_status", Status.APPROVE.toString());
            cars = carService.findFreeCars(criteriaCarMap, timeSearch);
            order = orderService.createOrderEntity(param);
            //order.setRequestDate(sdfTimestamp.parse(request.getParameter("requestDate")));
        } catch (ServiceException | ParseException e) {
            log.error("Catching: ", e);
        }

        session.setAttribute("cars", cars);
        request.setAttribute("order", order);

        request.getRequestDispatcher(PagePath.ADMIN_EDIT_ORDER_PAGE).forward(request, response);
    }
}

