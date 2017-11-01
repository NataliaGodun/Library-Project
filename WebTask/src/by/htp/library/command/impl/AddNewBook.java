package by.htp.library.command.impl;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import by.htp.library.command.Command;
import by.htp.library.domain.Book;
import by.htp.library.service.BookService;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.service.factory.ServiceFactory;


public class AddNewBook implements Command {
	private static final String NAME_BOOK = "nameBook";
	private static final String NAME_WRITER= "writer";
	private static final String ERROR_MESSAGE = "errorMessage";
	private static final String MESSAGE_FAILING_ADDITION  = "The book is not add!";
	private static final String MESSAGE_ABOUT_PROBLEM = "Sorry,technical problem";
	private static final String MESSAGE_SUCCESSFUL_ADDITION = "&Message=Book successful addition in library!";
	private static final String URL_VIEW_BOOK="http://localhost:8080/WebTask/Controller?command=viewBook&id=+";
	private static final String MAIN_JSP = "WEB-INF/jsp/main.jsp";
	private static final String ERROR_JSP = "error.jsp";
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
       
		String	nameBook = request.getParameter(NAME_BOOK);
		String writer = request.getParameter(NAME_WRITER);
		String	genre = request.getParameter("genre");
		String	house = request.getParameter("house");
		String	year = request.getParameter("year");
		
		 System.out.println( "pered fabricoi");
		ServiceFactory factory=ServiceFactory.getInstance();
		BookService bookService=factory.getBookService();
		
		Book book = null;
		String page = null;
		try {
			book = bookService.addBook( writer,nameBook,genre,house,year);
			 System.out.println(book.getId());
			if (book!=null)	{
				int i=book.getId();
				String url=URL_VIEW_BOOK+i;
				String url2=url+MESSAGE_SUCCESSFUL_ADDITION;
				 System.out.println( url2);
			response.sendRedirect(url2);
			
			}
			//else{
			//	request.setAttribute(ERROR_MESSAGE, MESSAGE_FAILING_ADDITION);
			//	page=MAIN_JSP;
			//	RequestDispatcher dispatcher=request.getRequestDispatcher(page);
			//	dispatcher.forward(request, response);
			//}
		} catch (ServiceException e) {
			System.out.println( "catch");
			request.setAttribute(ERROR_MESSAGE, MESSAGE_ABOUT_PROBLEM);
			page=MAIN_JSP;
			RequestDispatcher dispatcher=request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
			}	
			}
		
	}	
		
        

    
		
			



