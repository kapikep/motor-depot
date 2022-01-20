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
	
    String INDEX_PAGE = "index.jsp";
    String PRESENTATION_PAGE = "/WEB-INF/jsp/presentationPage.jsp";
    String ERROR_PAGE = "/WEB-INF/jsp/errorPage.jsp";

    
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
