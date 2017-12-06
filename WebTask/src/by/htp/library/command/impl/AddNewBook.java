package by.htp.library.command.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.library.command.Command;
import by.htp.library.domain.Book;
import by.htp.library.service.BookService;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.service.factory.ServiceFactory;

/**
 * @author Godun Natalia
 * @version 1.0
 */
public class AddNewBook implements Command {
	private static final String NAME_BOOK = "nameBook";
	private static final String NAME_WRITER = "writer";
	private static final String GENRE = "genre";
	private static final String HOUSE = "house";
	private static final String YEAR = "year";
	private static final String FILE = "file";
	private static final String ERROR_MESSAGE = "errorMessage";
	private static final String MESSAGE_ABOUT_PROBLEM = "Please, fill a form once again.";
	private static final String CONTENT_TYPE_TEXT_HTML = "text/html;charset=UTF-8";
	private static final String MESSAGE_SUCCESSFUL_ADDITION = "&message=Book successful addition in library!";
	private static final String URL_VIEW_BOOK = "http://localhost:8080/WebTask/Controller?command=viewBook&id=";
	private static final String ADD_NEW_BOOK_JSP = "WEB-INF/jsp/addNewBook.jsp";
	private static final String PATH_IMAGE = "C:/Users/Dima/git/Library-Project/WebTask/WebContent/resources/images/";
	private static final String MESSAGE_ERROR_ADDITION_BOOK = "Error at addition of the book.";

	private static final Logger LOGGER = LogManager.getRootLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE_TEXT_HTML);

		final String Writer = request.getParameter(NAME_WRITER);
		final String nameBook = request.getParameter(NAME_BOOK);
		final String genre = request.getParameter(GENRE);
		final String house = request.getParameter(HOUSE);
		final String year = request.getParameter(YEAR);
		final Part filePart = request.getPart(FILE);

		final String fileName = getFileName(filePart);

		String page = null;
		File uploadetFile = null;
		String pathImage;

		pathImage = (PATH_IMAGE + fileName);
		uploadetFile = new File(pathImage);

		// create file
		uploadetFile.createNewFile();

		OutputStream out = null;
		InputStream filecontent = null;
		final PrintWriter writer = response.getWriter();

		try {
			out = new FileOutputStream(new File(pathImage));

			filecontent = filePart.getInputStream();

			int read = 0;
			final byte[] bytes = new byte[1024];

			while ((read = filecontent.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

			ServiceFactory factory = ServiceFactory.getInstance();
			BookService bookService = factory.getBookService();

			Book book = new Book(0, Writer, nameBook, pathImage, genre, house, year);

			book = bookService.addBook(book);

			int i = book.getId();
			String url = URL_VIEW_BOOK + i;
			String urlWithMessage = url + MESSAGE_SUCCESSFUL_ADDITION;

			response.sendRedirect(urlWithMessage);

		} catch (ServiceException e) {

			LOGGER.log(Level.ERROR, MESSAGE_ERROR_ADDITION_BOOK, e);

			request.setAttribute(ERROR_MESSAGE, MESSAGE_ABOUT_PROBLEM);
			page = ADD_NEW_BOOK_JSP;

			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);

		} finally {
			if (out != null) {
				out.close();
			}
			if (filecontent != null) {
				filecontent.close();
			}
			if (writer != null) {
				writer.close();
			}
		}
	}

	private String getFileName(final Part part) {
		final String partHeader = part.getHeader("content-disposition");
		LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim().replace("\"", "").replace("\\", "")
						.replaceAll(":", "");
			}
		}
		return null;
	}

}
