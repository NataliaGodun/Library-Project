package by.htp.library.command.impl;

import java.io.IOException;

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

public class SearchBook implements Command {
	private static final String BOOK = "book";
	private static final String NAME_BOOK = "nameBook";
	private static final String VIEW_JSP = "WEB-INF/jsp/viewBook.jsp";
	private static final String URL_VIEW_ALL_BOOK_WITH_INFO="http://localhost:8080/WebTask/Controller?command=viewAllBooks&messageInfo=There is no such book in library!";
	private static final String URL_VIEW_ALL_BOOK_WITH_ERROR="http://localhost:8080/WebTask/Controller?command=viewAllBooks&messageInfo= Sorry,technical problem!";
	private static final String MESSAGE_LOGGER_INFO="The exception by a call of command SearchBook ";
			
	private static final Logger LOGGER = LogManager.getRootLogger();
	 
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nameBook=request.getParameter( NAME_BOOK);
		
		ServiceFactory factory=ServiceFactory.getInstance();
		BookService bookService=factory.getBookService();
		
		Book book = null;
		String page = null;
		try {
			book = bookService.searchBook(nameBook);
			if (!(book==null)){
				
				request.setAttribute(BOOK, book);
				page=VIEW_JSP;
				
				RequestDispatcher dispatcher=request.getRequestDispatcher(page);
				
				dispatcher.forward(request, response);
				
			}else{
				response.sendRedirect(URL_VIEW_ALL_BOOK_WITH_INFO);}
			   
		} catch (ServiceException e) {
			
			LOGGER.info(MESSAGE_LOGGER_INFO);
			
			response.sendRedirect(URL_VIEW_ALL_BOOK_WITH_ERROR);
		}
					
	}

}
