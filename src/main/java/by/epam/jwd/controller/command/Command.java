package by.epam.jwd.controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {

	String COMMAND = "Command";
	String CONTROLLER = "Controller";
    String LOG_OUT = "LogOut";
    String GO_TO_SIGN_IN = "GoToSignIn";
    String SIGN_IN = "SignIn";
    String GO_TO_MANAGE_CARS = "GoToManageCars";

    String DEFAULT_PATH = "/WEB-INF/jsp/";
    String INDEX_PAGE = "index.jsp";
    String SIGN_IN_PAGE = "signIn";
    String MAIN_ADMIN_PAGE = DEFAULT_PATH + "admin/mainAdminPage.jsp";
    String ACCESS_DENIED_PAGE = DEFAULT_PATH + "accessDeniedPage.jsp";
    String MAIN_DRIVER_PAGE = DEFAULT_PATH + "mainDriverPage.jsp";
    String MAIN_CUSTOMER_PAGE = DEFAULT_PATH + "mainCustomerPage.jsp";
    String MANAGE_CARS_PAGE = DEFAULT_PATH + "admin/manageCarsPage.jsp";

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
