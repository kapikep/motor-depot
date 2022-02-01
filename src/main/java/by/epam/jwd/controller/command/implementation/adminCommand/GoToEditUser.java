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

public class GoToEditUser implements Command {

    private final Logger log = LogManager.getLogger(GoToEditUser.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = MDServiceFactory.getMDService().getUserService();
        User user = null;
        String edit_id = request.getParameter("edit_id");

        try {
            if (edit_id != null) {
                user = userService.readUser(edit_id);
                request.setAttribute("edit", true);
            } else {
                request.setAttribute("create", true);
            }
            request.setAttribute("user", user);
        } catch (ServiceException e) {
            log.error("Catching: ", e);
        }
        request.getRequestDispatcher(PagePath.ADMIN_EDIT_USER_PAGE).forward(request, response);
    }
}

