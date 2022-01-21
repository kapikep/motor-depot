package by.epam.jwd.controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {

	String COMMAND = "Command";
	String CONTROLLER = "Controller";
	String GO_TO_SIGN_IN = "GoToSignIn";
	String GO_TO_PRESENTATION_PAGE = "GoToPresentationPage";
	String GO_TO_ADMIN_PAGE = "GoToAdminPage";

    String DEFAULT_PATH = "/WEB-INF/jsp/";
    String INDEX_PAGE = "index.jsp";
    String PRESENTATION_PAGE = "/WEB-INF/jsp/presentationPage.jsp";
    String SIGN_IN_PAGE = "/WEB-INF/jsp/signInPage.jsp";
    String MAIN_ADMIN_PAGE = "/WEB-INF/jsp/mainAdminPage.jsp";
    String ACCESS_DENIED_PAGE = DEFAULT_PATH + "accessDeniedPage.jsp";
    String MAIN_DRIVER_PAGE = DEFAULT_PATH + "mainDriverPage.jsp";
    String MAIN_CUSTOMER_PAGE = DEFAULT_PATH + "mainCustomerPage.jsp";
    String ERROR_PAGE = "/WEB-INF/jsp/errorPage.jsp";

    
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
