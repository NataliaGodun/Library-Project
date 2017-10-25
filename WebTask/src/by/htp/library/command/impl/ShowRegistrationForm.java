package by.htp.library.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import by.htp.library.command.Command;

public class ShowRegistrationForm implements Command {

	private static final String REGISTRATION_FORM_JSP ="WEB-INF/jsp/reg.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("loc") != null) {
			if (request.getParameter("loc").equals("ru")) {

				request.setAttribute("locale", "ru");
			}
	}
			
			RequestDispatcher dispatcher=request.getRequestDispatcher(REGISTRATION_FORM_JSP);
			dispatcher.forward(request, response);

		}

	}