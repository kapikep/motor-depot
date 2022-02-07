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
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GoToEditUser implements Command {

    private final Logger log = LogManager.getLogger(GoToEditUser.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = MDServiceFactory.getMDService().getUserService();
        String edit_id = request.getParameter("edit_id");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("wrongUser");

        try {
            if (user == null && edit_id != null && !("".equals(edit_id))) {
                user = userService.readUser(edit_id);
                session.setAttribute("editUserLogin", user.getLogin());
            }
            session.setAttribute("wrongUser", null);
            request.setAttribute("user", user);
        } catch (ServiceException e) {
            log.error("Catching: ", e);
        }

        request.getRequestDispatcher(PagePath.ADMIN_EDIT_USER_PAGE).forward(request, response);
    }
}

