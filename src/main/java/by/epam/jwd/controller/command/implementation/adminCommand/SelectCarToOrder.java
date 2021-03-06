package by.epam.jwd.controller.command.implementation.adminCommand;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.controller.command.implementation.customerCommand.GoToCustomerEditOrder;
import by.epam.jwd.controller.constant.PagePath;
import by.epam.jwd.dao.MotorDepotDAOFactory;
import by.epam.jwd.dao.interf.DriverDAO;
import by.epam.jwd.entity.*;
import by.epam.jwd.service.MDServiceFactory;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.interf.CarService;
import by.epam.jwd.service.interf.DriverService;
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
        DriverService driverService = MDServiceFactory.getMDService().getDriverService();
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
        List<Driver> drivers = new ArrayList<>();
        List<Driver> driversForCar = null;
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");

        EditOrder.fillingOrderParamMap(request, param);

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

            timeSearch.put("order_status", Status.APPROVE.toString());;
            cars = carService.findFreeCars(criteriaCarMap, timeSearch);
            order = orderService.createOrderEntity(param);
            for (Car car : cars) {
                driversForCar = driverService.findDrivers("attached_car_id", Integer.toString(car.getId()));
                if(!driversForCar.isEmpty()){
                    drivers.add(driversForCar.get(0));
                }
            }
        } catch (ServiceException | ParseException e) {
            log.error("Catching: ", e);
        }

        session.setAttribute("cars", cars);
        session.setAttribute("drivers", drivers);
        request.setAttribute("order", order);

        request.getRequestDispatcher(PagePath.ADMIN_EDIT_ORDER_PAGE).forward(request, response);
    }
}

