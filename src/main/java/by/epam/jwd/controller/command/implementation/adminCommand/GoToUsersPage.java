package by.epam.jwd.controller.command.implementation.adminCommand;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.controller.command.implementation.customerCommand.GoToCustomerEditOrder;
import by.epam.jwd.controller.constant.PagePath;
import by.epam.jwd.entity.User;
import by.epam.jwd.service.MDServiceFactory;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.interf.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoToUsersPage implements Command {

    private final Logger log = LogManager.getLogger(GoToUsersPage.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageCount = 1;
        List<User> users = null;
        List<Integer> numPages = null;
        String page = request.getParameter("page");
        String rowLimit = request.getParameter("rowLimit");
        UserService userService = MDServiceFactory.getMDService().getUserService();

        if (rowLimit != null && !("".equals(rowLimit))) {
            request.getSession().setAttribute("rowLimit", rowLimit);
        } else {
            rowLimit = (String) request.getSession().getAttribute("rowLimit");
        }

        if (page == null || "".equals(page)) {
            page = "1";
        }

        try {
            users = userService.readUsers(page, rowLimit);
            numPages = userService.pagination(page, rowLimit);
            pageCount = userService.getUserPageCount(rowLimit);
        } catch (ServiceException e) {
            log.error("Catching: ", e);
        }

        request.setAttribute("page", page);
        request.setAttribute("users", users);
        request.setAttribute("pageCount", pageCount);
        request.setAttribute("numPages", numPages);
        request.getRequestDispatcher(PagePath.ADMIN_USERS_PAGE).forward(request, response);
    }
}
