package by.epam.jwd.controller.command.implementation.adminCommand;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.controller.command.implementation.customerCommand.GoToCustomerEditOrder;
import by.epam.jwd.controller.constant.PagePath;
import by.epam.jwd.entity.Car;
import by.epam.jwd.entity.Order;
import by.epam.jwd.entity.Status;
import by.epam.jwd.service.MDServiceFactory;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.interf.CarService;
import by.epam.jwd.service.interf.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        SimpleDateFormat timestamp = new SimpleDateFormat("yy-MM-dd HH:mm:ss.SSS");
        Map<String, String> criteriaCarMap = new HashMap<>();
        Map<String , String> timeSearch = new HashMap<>();
        List<Car> cars = null;
        Car car = null;
        Order order = null;
        String edit_id = request.getParameter("edit_id");
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");
        request.setAttribute("createStep2", true);

        if (edit_id != null && !("".equals(edit_id))) {
            request.setAttribute("edit", true);
        } else {
            request.setAttribute("create", true);
        }
        try {
                criteriaCarMap.put("load_capacity", request.getParameter("loadCapacity"));
                criteriaCarMap.put("passenger_capacity", request.getParameter("passengerCapacity"));
                criteriaCarMap.put("status", "active");
                criteriaCarMap.put("type", request.getParameter("carType"));

                order = new Order();
                order.setRequestDate(new Date());
                order.setAdminName((String) request.getSession().getAttribute("userFullName"));
                order.setCriteria(request.getParameter("criteria"));
                order.setDepartPlace(request.getParameter("departPlace"));
                order.setArrivalPlace(request.getParameter("arrivalPlace"));
                if(startDateStr != null && !("".equals(startDateStr))){
                    order.setStartDate(sdf.parse(startDateStr));
                    timeSearch.put("start_date>", timestamp.format(order.getStartDate()));
                }
                if(endDateStr != null && !("".equals(endDateStr))){
                    order.setEndDate(sdf.parse(endDateStr));
                    timeSearch.put("end_date<", timestamp.format(order.getEndDate()));
                }

                timeSearch.put("order_status", Status.APPROVE.toString());
                order.setClientFullName(request.getParameter("clientFullName"));
                order.setClientPhone(request.getParameter("clientPhone"));
                cars = carService.findFreeCars(criteriaCarMap, timeSearch);


            request.setAttribute("order", order);
            request.setAttribute("cars", cars);
        } catch (ServiceException | ParseException e) {
            log.error("Catching: ", e);
        }
        request.getRequestDispatcher(PagePath.ADMIN_EDIT_ORDER_PAGE).forward(request, response);
    }
}

