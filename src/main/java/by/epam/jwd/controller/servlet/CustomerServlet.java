package by.epam.jwd.controller.servlet;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.controller.command.CommandProvider;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {
    private final CommandProvider provider = new CommandProvider();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);

    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter("command");

        if (commandName != null) {
            Command command = provider.getCommand(commandName);
            command.execute(request, response);
        } else {
            throw new IOException("Access denied");
        }
    }
}
