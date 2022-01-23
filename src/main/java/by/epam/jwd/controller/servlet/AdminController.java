package by.epam.jwd.controller.servlet;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.controller.command.CommandProvider;
import by.epam.jwd.entity.Role;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/admin")
public class AdminController extends HttpServlet {
    private final CommandProvider provider = CommandProvider.getCommandProvider();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);

    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String commandName = request.getParameter("command");
        HttpSession session = request.getSession();
        session.setAttribute("role", Role.ADMIN);
        session.setAttribute("name", "Dmitrij");

        if(session.getAttribute("role") == Role.ADMIN) {
            if (commandName != null) {
                Command command = provider.getAdminCommand(commandName);
                command.execute(request, response);
            } else {
                request.getRequestDispatcher(Command.MAIN_ADMIN_PAGE).forward(request, response);
            }
        }else {
            response.sendRedirect("signIn");
        }
    }
}
