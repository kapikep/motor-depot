package by.epam.jwd.controller.command.implementation;

import by.epam.jwd.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class ChangeLocalization implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String localeStr = request.getParameter("locale");
        Locale locale = new Locale(localeStr);
        ResourceBundle bundle = ResourceBundle.getBundle("localization.local", locale);
        request.getSession().setAttribute("locale", localeStr);
        request.getSession().setAttribute("bundle", bundle);

        response.sendRedirect(request.getRequestURI() + "?command=" + request.getParameter("prev_command") +
                "&edit_id=" + request.getParameter("edit_id") + "&flag=" + request.getParameter("flag"));
    }
}
