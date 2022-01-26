package by.epam.jwd.controller.servlet;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.controller.command.CommandProvider;
import by.epam.jwd.entity.Role;

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

        if(session.getAttribute("role") == Role.ADMIN) {
            String rowLimitStr = request.getParameter("rowLimit");
            if(rowLimitStr != null){
                request.getSession().setAttribute("rowLimit", rowLimitStr);
            }
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

    public static List<Integer> pagination(int page, int pageCount){
        List<Integer> res = new ArrayList<>();

        for (int i = page - 3; i <= page; i++) {
            if(i >= 1){
                res.add(i);
            }
        }

        for (int i = page + 1; i <= page + 3; i++) {
            if(i <= pageCount){
                res.add(i);
            }
        }
        return res;
    }
}
