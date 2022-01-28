package by.epam.jwd.controller.servlet;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.controller.command.CommandProvider;
import by.epam.jwd.controller.constant.PagePath;
import by.epam.jwd.entity.Role;
import by.epam.jwd.service.ServiceUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

        if (session.getAttribute("role") == Role.ADMIN) {
            pagination(request, response);
            if (commandName != null && !("".equals(commandName))) {
                Command command = provider.getAdminCommand(commandName);
                command.execute(request, response);
            } else {
                request.getRequestDispatcher(PagePath.MAIN_ADMIN_PAGE).forward(request, response);
            }
        } else {
            response.sendRedirect("signIn");
        }
    }

    private void pagination(HttpServletRequest request, HttpServletResponse response) {
        String rowLimit = ServiceUtil.checkRowLimit(request.getParameter("rowLimit"));;
        String page =  ServiceUtil.checkPage(request.getParameter("page"));

        if(rowLimit != null){
            request.getSession().setAttribute("rowLimit", rowLimit);
        }

        request.setAttribute("page", page);
    }
}
