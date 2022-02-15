package by.epam.jwd.controller.command.implementation.driverCommand;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.controller.constant.CommandName;
import by.epam.jwd.entity.Car;
import by.epam.jwd.entity.Order;
import by.epam.jwd.service.MDServiceFactory;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.ServiceUtil;
import by.epam.jwd.service.ValidateException;
import by.epam.jwd.service.interf.CarService;
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

public class UpdateOrderByDriver implements Command {

    private final Logger log = LogManager.getLogger(UpdateOrderByDriver.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ResourceBundle bundle = (ResourceBundle) session.getAttribute("bundle");
        OrderService orderService = MDServiceFactory.getMDService().getOrderService();
        CarService carService = MDServiceFactory.getMDService().getCarService();
        String resMessage = null;
        boolean exception = false;
        String editId = request.getParameter("edit_id");
        Map<String, String> param = new HashMap<>();
        Order order = null;
        Car car = null;

        param.put("editId", editId);
        param.put("criteria", request.getParameter("criteria"));
        param.put("departPlace", request.getParameter("departPlace"));
        param.put("arrivalPlace", request.getParameter("arrivalPlace"));
        param.put("startDate", request.getParameter("startDate"));
        param.put("endDate", request.getParameter("endDate"));
        param.put("distance", request.getParameter("distance"));
        param.put("status", request.getParameter("orderStatus"));

        try {
            order = orderService.updateOrder(editId, param);
            car = carService.readCar(order.getCarId());
            car.setStatus(request.getParameter("carStatus"));
            car.setOdometr(ServiceUtil.parseInt(request.getParameter("odometr")));
            carService.updateCar(car);
            session.setAttribute("wrongInputOrder", null);
            resMessage = bundle.getString("message.updateDone");
        } catch (ServiceException e) {
            exception = true;
            resMessage = bundle.getString("message.somethingWrong");
            log.error("Catching: ", e);
        } catch (ValidateException e) {
            exception = true;
            resMessage = e.getLocalizedMessage();
        }

        if (exception) {
            response.sendRedirect(CommandName.DRIVER_COMMAND + CommandName.GO_TO_DRIVER_EDIT_ORDER + "&message=" +
                    URLEncoder.encode(resMessage, "UTF-8") + "&edit_id=" + editId);
        } else {
            response.sendRedirect(CommandName.DRIVER_COMMAND + CommandName.GO_TO_DRIVER_ORDERS_PAGE + "&message=" +
                    URLEncoder.encode(resMessage, "UTF-8"));
        }
    }
}

