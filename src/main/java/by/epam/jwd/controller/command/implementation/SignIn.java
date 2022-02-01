package by.epam.jwd.controller.command.implementation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.controller.command.implementation.customerCommand.GoToCustomerEditOrder;
import by.epam.jwd.controller.constant.CommandName;
import by.epam.jwd.entity.Status;
import by.epam.jwd.entity.User;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.MDServiceFactory;
import by.epam.jwd.service.interf.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SignIn implements Command {

    private final Logger log = LogManager.getLogger(SignIn.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = MDServiceFactory.getMDService().getUserService();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        User user = null;

        try {
            user = userService.authorization(login, password);
        } catch (ServiceException e) {
            log.error("Catching: ", e);
        }

        if (user != null && user.getStatus() == Status.ACTIVE) {

            session.setAttribute("role", user.getRole());
            session.setAttribute("userFullName", user.getName() + " " + user.getSurname());
            session.setAttribute("userId", user.getId());

            switch (user.getRole()) {
                case ADMIN:
                    response.sendRedirect("admin");
                    break;
                case CUSTOMER:
                    response.sendRedirect("customer");
                    break;
                case DRIVER:
                    response.sendRedirect("driver");
                    break;
            }

        } else {
            if (user == null) {
                session.setAttribute("access", "Invalid login or password");
            } else {
                session.setAttribute("access", "Account block");
            }
            response.sendRedirect(CommandName.WELCOME_COMMAND + CommandName.GO_TO_SIGN_IN);
        }
    }
}
