package by.epam.jwd.controller.servlet;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.controller.command.CommandProvider;
import by.epam.jwd.controller.constant.PagePath;
import by.epam.jwd.entity.Role;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/driver")
public class DriverController extends HttpServlet {

    private final CommandProvider provider = CommandProvider.getCommandProvider();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);

    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String commandName = request.getParameter("command");
        HttpSession session = request.getSession(true);

        if(session.getAttribute("role") == Role.DRIVER) {
            if (commandName != null) {
                Command command = provider.getDriverCommand(commandName);
                command.execute(request, response);
            } else {
                request.getRequestDispatcher(PagePath.MAIN_DRIVER_PAGE).forward(request, response);
            }
        }else {
            response.sendRedirect("signIn");
        }
    }
}

