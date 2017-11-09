package by.htp.library.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.htp.library.command.Command;
import by.htp.library.service.BookService;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.service.factory.ServiceFactory;

public class DeleteBook implements Command {
	private static final String ID ="id";
	private static final String ERROR_MESSAGE = "errorMessage";
	private static final String MESSAGE_FAIL_DELETE = "The book is not delete!";
	private static final String MESSAGE_SUCCESSFUL_DELETE = "&Message=Book successful delete!";
	private static final String URL_VIEW_ALL_BOOK=" http://localhost:8080/WebTask/Controller?command=viewAllBooks";
	private static final String VIEW_JSP = "WEB-INF/jsp/viewBook.jsp";
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idBook=request.getParameter(ID);
		 
		int id=Integer.parseInt(idBook);
		
		
		ServiceFactory factory=ServiceFactory.getInstance();
		BookService bookService=factory.getBookService();
	
		String page = null;
		try {
			 bookService.deleteBook(id);
				String url= URL_VIEW_ALL_BOOK;
				String url2=url+MESSAGE_SUCCESSFUL_DELETE;
			    response.sendRedirect(url2);
		
		} catch (ServiceException e) {
			request.setAttribute(ERROR_MESSAGE, MESSAGE_FAIL_DELETE);
			page=VIEW_JSP;
			RequestDispatcher dispatcher=request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}		
			
	}	

}
