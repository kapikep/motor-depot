package by.epam.jwd.controller.command.implementation;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.controller.constant.CommandName;
import by.epam.jwd.controller.constant.PagePath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToWelcomeCreateOrder implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(PagePath.WELCOME_CREATE_ORDER).forward(request, response);
    }
}
