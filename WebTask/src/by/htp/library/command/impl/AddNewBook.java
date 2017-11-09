package by.htp.library.command.impl;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import by.htp.library.command.Command;
import by.htp.library.domain.Book;
import by.htp.library.service.BookService;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.service.factory.ServiceFactory;


public class AddNewBook implements Command {
	private static final String NAME_BOOK = "nameBook";
	private static final String NAME_WRITER= "writer";
	private static final String GENRE= "genre";
	private static final String HOUSE= "house";
	private static final String YEAR= "year";
	private static final String FILE= "file";
	private static final String ERROR_MESSAGE = "errorMessage";
	private static final String MESSAGE_ABOUT_PROBLEM = "Sorry,technical problem";
	private static final String CONTENT_TYPE_TEXT_HTML= "text/html;charset=UTF-8";
	//private static final String MESSAGE_SUCCESSFUL_ADDITION = "&Message=Book successful addition in library!";
	private static final String URL_VIEW_BOOK="http://localhost:8080/WebTask/Controller?command=viewBook&id=";
	private static final String MAIN_JSP = "WEB-INF/jsp/main.jsp";
	private static final String PATH_IMAGE = "C:/Users/Dima/git/Library-Project/WebTask/WebContent/resources/images/";
	private static final String JPG= ".jpg";
	
	private Random random = new Random();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 response.setContentType(CONTENT_TYPE_TEXT_HTML);
		 final String Writer=request.getParameter(NAME_WRITER);
		 final String nameBook=request.getParameter(NAME_BOOK);
		 final String genre=request.getParameter(GENRE);
		 final String house=request.getParameter(HOUSE);
		 final String year =request.getParameter(YEAR);
		 final Part filePart=request.getPart(FILE);
		   
		 File uploadetFile = null;
	
		 String pathImage;
		 String page = null;
	
		//we choose to the file a name we won't find free yet
		 do{
			 pathImage = (PATH_IMAGE + random.nextInt(1000)+JPG);	
				uploadetFile = new File(pathImage);		
			}while(uploadetFile.exists());
		
			//create file
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
		       
				ServiceFactory factory=ServiceFactory.getInstance();
				BookService bookService=factory.getBookService();
				
				Book book = new Book (0, Writer,nameBook,pathImage,genre,house,year);				
				
				book = bookService.addBook( book);
					
				int i=book.getId();
				String url=URL_VIEW_BOOK+i;
				
				response.sendRedirect(url);
						
				} catch (ServiceException e) {
					request.setAttribute(ERROR_MESSAGE, MESSAGE_ABOUT_PROBLEM);
					page=MAIN_JSP;
					RequestDispatcher dispatcher=request.getRequestDispatcher(page);
					dispatcher.forward(request, response);
					}	
						        
		    finally {
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
}
	
	
        

    
		
			



