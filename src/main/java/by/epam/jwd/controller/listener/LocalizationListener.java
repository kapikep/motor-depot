package by.epam.jwd.controller.listener;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.util.ResourceBundle;

@WebListener
public class LocalizationListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    public LocalizationListener() {
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().setAttribute("bundle", ResourceBundle.getBundle("localization.local"));
        se.getSession().setAttribute("locale", "en");
    }
}
