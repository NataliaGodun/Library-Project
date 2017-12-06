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
public class ShowAddNewBookForm implements Command {

	private static final String ADD_NEW_BOOK_JSP = "WEB-INF/jsp/addNewBook.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher(ADD_NEW_BOOK_JSP);

		dispatcher.forward(request, response);
	}

}
