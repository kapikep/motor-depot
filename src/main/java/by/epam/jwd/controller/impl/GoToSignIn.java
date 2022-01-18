package by.epam.jwd.controller.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.jwd.controller.Command;

public class GoToSignIn implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GoToSignIn");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/signIn.jsp");
		dispatcher.forward(request, response);

	}

}
