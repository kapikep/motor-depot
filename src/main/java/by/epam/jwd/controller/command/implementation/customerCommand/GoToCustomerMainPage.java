package by.epam.jwd.controller.command.implementation.customerCommand;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.controller.constant.PagePath;
import by.epam.jwd.entity.Order;
import by.epam.jwd.entity.Status;
import by.epam.jwd.service.MDServiceFactory;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.interf.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoToCustomerMainPage implements Command {

    private final Logger log = LogManager.getLogger(GoToCustomerMainPage.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int pageCount = 1;
        List<Order> orders = null;
        List<Integer> numPages = null;
        String page = request.getParameter("page");
        String rowLimit = request.getParameter("rowLimit");
        OrderService orderService = MDServiceFactory.getMDService().getOrderService();
        int userId = (int) request.getSession().getAttribute("userId");

        if (rowLimit != null && !("".equals(rowLimit))) {
            request.getSession().setAttribute("rowLimit", rowLimit);
        } else {
            rowLimit = (String) request.getSession().getAttribute("rowLimit");
        }

        if (page == null || "".equals(page)) {
            page = "1";
        }

        try {
            orders = orderService.readOrders(page, rowLimit, "client_id", Integer.toString(userId));
            numPages = orderService.pagination(page, rowLimit);
            pageCount = orderService.getOrderPageCount(rowLimit);
        } catch (ServiceException e) {
            log.error("Catching: ", e);
        }

        request.setAttribute("page", page);
        request.setAttribute("orders", orders);
        request.setAttribute("pageCount", pageCount);
        request.setAttribute("numPages", numPages);

        request.getRequestDispatcher(PagePath.MAIN_CUSTOMER_PAGE).forward(request, response);
    }
}
