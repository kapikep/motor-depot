package by.epam.jwd.controller.servlet;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.controller.command.CommandProvider;
import by.epam.jwd.controller.constant.PagePath;
import by.epam.jwd.entity.Role;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/customer")
public class CustomerController extends HttpServlet {
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

        if(session.getAttribute("role") == Role.CUSTOMER) {
            if (commandName != null) {
                Command command = provider.getCustomerCommand(commandName);
                command.execute(request, response);
            } else {
                request.getRequestDispatcher(PagePath.MAIN_CUSTOMER_PAGE).forward(request, response);
            }
        }else {
            response.sendRedirect("signIn");
        }
    }
}
