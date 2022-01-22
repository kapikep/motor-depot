package by.epam.jwd.controller.command.implementation;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.entity.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToSignIn implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Role role = (Role)request.getSession().getAttribute("role");
        if(role == null){
            response.sendRedirect(SIGN_IN_PAGE);
        }else{
            switch (role) {
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
        }
    }
}
