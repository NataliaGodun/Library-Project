package by.htp.library.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.library.command.Command;
import by.htp.library.domain.Book;
import by.htp.library.service.BookService;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.service.factory.ServiceFactory;

public class ViewAllBooks implements Command {
	private static final String LIST = "List";
	private static final String MESSAGE_NO_BOOKS = " There are no available books";
	private static final String MESSAGE_ABOUT_PROBLEM = "Sorry,technical problem";
	private static final String TAKE_ALL_JSP = "WEB-INF/jsp/takeAllBook.jsp";
	private static final String ERROR_MESSAGE = "errorMessage";
	private static final String MESSAGE_INFO = "messageInfo";
	private static final String MESSAGE = "message";
	private static final String MESSAGE_LOGGER_INFO = "The exception has occurred upon transition to the page WEB-INF/jsp/takeAllBook.jsp";

	private static final Logger LOGGER = LogManager.getRootLogger();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	ServiceFactory factory = ServiceFactory.getInstance();
	BookService bookService = factory.getBookService();

	try
	{
		ArrayList<Book> List = bookService.showBooks();
		if (List.size() == 0) {

			request.setAttribute(MESSAGE_INFO, MESSAGE_NO_BOOKS);
			
		} else {
			String messageInfo= request.getParameter(MESSAGE_INFO);//Book successful delete or no such book
			request.setAttribute(MESSAGE_INFO, messageInfo);

			String messageWrongAuthorization = request.getParameter(MESSAGE);
			request.setAttribute(MESSAGE, messageWrongAuthorization);
			
			String messageErrorAuthorization = request.getParameter(ERROR_MESSAGE);
			request.setAttribute(ERROR_MESSAGE, messageErrorAuthorization);
			
			request.setAttribute(LIST, List);
			
		}
	}catch(ServiceException e){
		
		LOGGER.info(MESSAGE_LOGGER_INFO );
		
		request.setAttribute(ERROR_MESSAGE, MESSAGE_ABOUT_PROBLEM);
		
	}

	RequestDispatcher dispatcher = request.getRequestDispatcher(TAKE_ALL_JSP);

	dispatcher.forward(request,response);


	}

}