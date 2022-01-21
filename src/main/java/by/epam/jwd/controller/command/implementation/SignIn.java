package by.epam.jwd.controller.command.implementation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.entity.Status;
import by.epam.jwd.entity.User;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.UserService;
import by.epam.jwd.service.implementation.UserServiceImpl;

public class SignIn implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");
        HttpSession session = request.getSession(true);
        User user = null;

        try {
            user = userService.authorization(login, password);
            System.out.println(user);
        } catch (ServiceException e) {
            e.printStackTrace();
            //TODO
        }

        if (user != null && user.getStatus() == Status.ACTIVE) {

            if(remember != null){
                session.setAttribute("role", user.getRole());
                session.setAttribute("userId", user.getId());
            }


            request.setAttribute("userName", user.getName());

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
            request.setAttribute("userName", login);
            request.getRequestDispatcher(ACCESS_DENIED_PAGE).forward(request, response);
        }
    }
}
