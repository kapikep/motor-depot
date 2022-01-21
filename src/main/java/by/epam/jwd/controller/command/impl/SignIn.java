package by.epam.jwd.controller.command.impl;

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

            if(remember == null){
                session.setAttribute("role", user.getRole());
            }


            request.setAttribute("userName", user.getName());

            switch (user.getRole()) {
                case ADMIN:
                    request.getRequestDispatcher(MAIN_ADMIN_PAGE).forward(request, response);
                    break;
                case CUSTOMER:
					request.getRequestDispatcher(MAIN_CUSTOMER_PAGE).forward(request, response);
                    break;
                case DRIVER:
					request.getRequestDispatcher(MAIN_DRIVER_PAGE).forward(request, response);
                    break;
            }

        } else {
            request.setAttribute("userName", login);
            request.getRequestDispatcher(ACCESS_DENIED_PAGE).forward(request, response);
        }
    }
}
