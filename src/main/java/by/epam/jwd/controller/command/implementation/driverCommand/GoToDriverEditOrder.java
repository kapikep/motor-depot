package by.epam.jwd.controller.command.implementation.driverCommand;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.controller.command.implementation.customerCommand.GoToCustomerEditOrder;
import by.epam.jwd.controller.constant.PagePath;
import by.epam.jwd.entity.Car;
import by.epam.jwd.entity.CarModel;
import by.epam.jwd.entity.Order;
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
import java.util.List;

public class GoToDriverEditOrder implements Command {

    private final Logger log = LogManager.getLogger(GoToCustomerEditOrder.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderService orderService = MDServiceFactory.getMDService().getOrderService();
        CarService carService = MDServiceFactory.getMDService().getCarService();
        Car car = null;
        Order order = (Order) request.getSession().getAttribute("wrongEnteredOrder");
        String editId = request.getParameter("edit_id");
        try {
            if(editId != null && !("".equals(editId))) {
                order = orderService.readOrder(editId);
                car = carService.readCar(Integer.toString(order.getCarId()));
            }else {
                request.getRequestDispatcher(PagePath.ORDERS_DRIVER_PAGE).forward(request, response);
            }
            request.setAttribute("order", order);
            request.setAttribute("car", car);
        } catch (ServiceException e) {
            log.error("Catching: ", e);
        }
        request.getRequestDispatcher(PagePath.DRIVER_EDIT_ORDER).forward(request, response);
    }
}
