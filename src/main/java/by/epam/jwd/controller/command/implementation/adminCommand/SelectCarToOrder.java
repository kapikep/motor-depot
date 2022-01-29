package by.epam.jwd.controller.command.implementation.adminCommand;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.controller.constant.PagePath;
import by.epam.jwd.entity.Car;
import by.epam.jwd.entity.CarModel;
import by.epam.jwd.entity.Order;
import by.epam.jwd.service.MDServiceFactory;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.interf.CarService;
import by.epam.jwd.service.interf.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectCarToOrder implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderService orderService = MDServiceFactory.getMDService().getOrderService();
        CarService carService = MDServiceFactory.getMDService().getCarService();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Map<String, String> criteriaMap = new HashMap<>();
        List<Order> orders = null;
        List<CarModel> carModels = null;
        List<Car> cars = null;
        Car car = null;
        Order order = null;
        String edit_id = request.getParameter("edit_id");
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");
        try {
            if (edit_id != null && !("".equals(edit_id))) {
                car = carService.readCar(edit_id);
                order = orderService.readOrder(edit_id);
                request.setAttribute("edit", true);
            } else {
                request.setAttribute("createStep2", true);
                order = new Order();
                order.setRequestDate(new Date());
                order.setAdminName((String) request.getSession().getAttribute("userFullName"));
                order.setCriteria(request.getParameter("criteria"));
                order.setDepartPlace(request.getParameter("departPlace"));
                order.setArrivalPlace(request.getParameter("arrivalPlace"));
                if(startDateStr != null && !("".equals(endDateStr))){
                    order.setStartDate(sdf.parse(startDateStr));
                }
                if(endDateStr != null && !("".equals(endDateStr))){
                    order.setStartDate(sdf.parse(endDateStr));
                }
                order.setClientFullName(request.getParameter("clientFullName"));
                order.setClientPhone(request.getParameter("clientPhone"));

                criteriaMap.put("type", request.getParameter("carType"));
                cars = carService.findCars(criteriaMap);
            }
            request.setAttribute("order", order);
            request.setAttribute("cars", cars);
        } catch (ServiceException e) {
            //TODO logger
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher(PagePath.ADMIN_EDIT_ORDER_PAGE).forward(request, response);
    }
}

