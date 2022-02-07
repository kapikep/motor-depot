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
        CarService carService = MDServiceFactory.getMDService().getCarService();
        UserService userService = MDServiceFactory.getMDService().getUserService();
        SimpleDateFormat sdfTimestamp = new SimpleDateFormat("yy-MM-dd HH:mm:ss.SSS");
        String edit_id = request.getParameter("edit_id");
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("enteredOrder");
        String adminName = (String) session.getAttribute("userFullName");
        User user = null;
        List<Order> orders = null;
        List<CarModel> carModels = null;
        List<String> carTypes = null;
        Car car = null;
        List<Car> cars = new ArrayList<>();
        try {
            if(order == null && edit_id != null && !("".equals(edit_id))) {
                order = orderService.readOrder(edit_id);
                cars.add(carService.readCar(order.getCarId()));

                if(order.getClientId() != 0){
                    user = userService.readUser(order.getClientId());
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

        request.setAttribute("step", "1");
        request.setAttribute("carTypes", carTypes);
        request.setAttribute("user", user);
        request.setAttribute("order", order);
        request.setAttribute("cars", cars);
        request.getRequestDispatcher(PagePath.ADMIN_EDIT_ORDER_PAGE).forward(request, response);
    }

    private List<Car> searchCar(HttpServletRequest request, HttpServletResponse response){
        CarService carService = MDServiceFactory.getMDService().getCarService();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        SimpleDateFormat timestamp = new SimpleDateFormat("yy-MM-dd HH:mm:ss.SSS");
        Map<String, String> criteriaCarMap = new HashMap<>();
        Map<String , String> timeSearch = new HashMap<>();
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");
        List<Car> cars = null;
        try {
            criteriaCarMap.put("load_capacity", request.getParameter("loadCapacity"));
            criteriaCarMap.put("passenger_capacity", request.getParameter("passengerCapacity"));
            criteriaCarMap.put("status", "active");
            criteriaCarMap.put("type", request.getParameter("carType"));

            if(startDateStr != null && !("".equals(startDateStr))){
                timeSearch.put("start_date>", timestamp.format(startDateStr));
            }
            if(endDateStr != null && !("".equals(endDateStr))){
                timeSearch.put("end_date<", timestamp.format(endDateStr));
            }

            timeSearch.put("order_status", Status.APPROVE.toString());
            cars = carService.findFreeCars(criteriaCarMap, timeSearch);
        } catch (ServiceException e) {
            log.error("Catching: ", e);
        }
        return cars;
    }
}
