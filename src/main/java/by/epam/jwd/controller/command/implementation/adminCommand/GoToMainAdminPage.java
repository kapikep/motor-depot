package by.epam.jwd.controller.command.implementation.adminCommand;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.controller.constant.PagePath;
import by.epam.jwd.entity.Car;
import by.epam.jwd.entity.Order;
import by.epam.jwd.entity.Status;
import by.epam.jwd.service.MDServiceFactory;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.interf.CarService;
import by.epam.jwd.service.interf.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoToMainAdminPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderService orderService = MDServiceFactory.getMDService().getOrderService();
        String rowLimit = (String) request.getSession().getAttribute("rowLimit");
        String page = (String) request.getAttribute("page");
        int pageCount = 1;
        List<Order> orders = null;
        List<Integer> numPages = null;
        String id = request.getParameter("findId");

        if (id == null) {
            try {
                orders = orderService.readOrders(page, rowLimit, "order_status", Status.NOT_APPROVE.toString());
                numPages = orderService.pagination(page, rowLimit);
                pageCount = orderService.getOrderPageCount(rowLimit);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        } else {
            try {
                orders = orderService.readOrders("id", id);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }

        request.setAttribute("orders", orders);
        request.setAttribute("pageCount", pageCount);
        request.setAttribute("numPages", numPages);
        request.getRequestDispatcher(PagePath.MAIN_ADMIN_PAGE).forward(request, response);
    }
}

