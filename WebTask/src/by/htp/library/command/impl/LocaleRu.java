package by.htp.library.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.library.command.Command;

/**
 * @author Godun Natalia
 * @version 1.0
 */
public class LocaleRu implements Command {
	private static final String LOCALE_RU= "WEB-INF/jsp/localeRu.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher=request.getRequestDispatcher(LOCALE_RU);
		
		dispatcher.forward(request, response);
	}

}
