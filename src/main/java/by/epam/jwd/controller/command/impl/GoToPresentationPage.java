package by.epam.jwd.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.controller.command.PagePath;

public class GoToPresentationPage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher(PagePath.PRESENTATION_PAGE).forward(request, response);

	}

}
