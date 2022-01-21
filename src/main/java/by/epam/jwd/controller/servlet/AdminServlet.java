package by.epam.jwd.controller.servlet;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.controller.command.CommandProvider;
import by.epam.jwd.entity.Role;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private final CommandProvider provider = new CommandProvider();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);

    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String commandName = request.getParameter("command");
        HttpSession session = request.getSession(true);
        System.out.println(session.getAttribute("role"));

        if(session.getAttribute("role") == Role.ADMIN) {
            if (commandName != null) {
                Command command = provider.getCommand(commandName);
                command.execute(request, response);
            } else {
                request.getRequestDispatcher(Command.MAIN_ADMIN_PAGE).forward(request, response);
            }
        }else {
            throw new IOException("Access denied");
        }
    }
}
