package by.epam.jwd.controller.command.implementation.driverCommand;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.controller.constant.CommandName;
import by.epam.jwd.entity.Car;
import by.epam.jwd.entity.Order;
import by.epam.jwd.service.MDServiceFactory;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.ValidateException;
import by.epam.jwd.service.interf.CarService;
import by.epam.jwd.service.interf.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UpdateOrderByDriver implements Command {

    private final Logger log = LogManager.getLogger(UpdateOrderByDriver.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        OrderService orderService = MDServiceFactory.getMDService().getOrderService();
        CarService carService = MDServiceFactory.getMDService().getCarService();
        String message = "Update done";

        Map<String, String> param = new HashMap<>();
        Order order = null;
        Car car = null;

        String edit_id = request.getParameter("edit_id");
        try {
            order = orderService.readOrder(edit_id);
            car = carService.readCar(order.getCarId());
            car.setStatus(request.getParameter("carStatus"));
            carService.updateCar(car);
        } catch (ServiceException e) {
            log.error("Catching: ", e);
        }

        param.put("editId", request.getParameter("edit_id"));
        param.put("criteria", request.getParameter("criteria"));
        param.put("departPlace", request.getParameter("departPlace"));
        param.put("arrivalPlace", request.getParameter("arrivalPlace"));
        param.put("startDate", request.getParameter("startDate"));
        param.put("endDate", request.getParameter("endDate"));
        param.put("distance", request.getParameter("distance"));
        param.put("totalAmount", Integer.toString(order.getTotalAmount()));
        param.put("paymentStatus", order.getPaymentStatus());
        param.put("status", request.getParameter("status"));
        param.put("clientFullName", order.getClientFullName());
        param.put("clientPhone", order.getClientPhone());
        param.put("adminName", order.getAdminName());
        param.put("carId", Integer.toString(order.getCarId()));
        param.put("clientId", Integer.toString(order.getClientId()));
        param.put("adminId", Integer.toString(order.getAdminId()));
        param.put("driverId", request.getSession().getAttribute("userId").toString());

            try {
                orderService.updateOrder(param);
            } catch (ServiceException e) {
                message = "Something wrong";
                log.error("Catching: ", e);
            } catch (ValidateException e) {
                e.printStackTrace();
            }
        response.sendRedirect(CommandName.DRIVER_COMMAND + CommandName.GO_TO_DRIVER_ORDERS_PAGE + "&message=" + message);
    }
}

