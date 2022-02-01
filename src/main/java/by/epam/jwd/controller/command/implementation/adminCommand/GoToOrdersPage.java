package by.epam.jwd.controller.command.implementation.adminCommand;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.controller.constant.PagePath;
import by.epam.jwd.entity.Order;
import by.epam.jwd.service.MDServiceFactory;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.interf.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GoToOrdersPage implements Command {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageCount = 1;
        List<Order> orders = null;
        List<Integer> numPages = null;
        String page = request.getParameter("page");
        String rowLimit = request.getParameter("rowLimit");
        String id = request.getParameter("findId");
        OrderService orderService = MDServiceFactory.getMDService().getOrderService();

        if (rowLimit != null && !("".equals(rowLimit))) {
            request.getSession().setAttribute("rowLimit", rowLimit);
        }else {
            rowLimit = (String) request.getSession().getAttribute("rowLimit");
        }

        if (page == null || "".equals(page)) {
            page = "1";
        }

        if (id == null) {
            try {
                orders = orderService.readOrders(page, rowLimit);
                numPages = orderService.pagination(page, rowLimit);
                pageCount = orderService.getOrderPageCount(rowLimit);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        } else {
            try {
                orders = new ArrayList<>();
                orders.add(orderService.readOrder(id));
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }

        request.setAttribute("page", page);
        request.setAttribute("orders", orders);
        request.setAttribute("pageCount", pageCount);
        request.setAttribute("numPages", numPages);
        request.getRequestDispatcher(PagePath.ADMIN_ORDERS_PAGE).forward(request, response);
    }
}
