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
	private static final String ERROR_MESSAGE = "errorMessage";
	private static final String MESSAGE_FAILING_ADDITION  = "The book is not add!";
	private static final String MESSAGE_ABOUT_PROBLEM = "Sorry,technical problem";
	private static final String MESSAGE_SUCCESSFUL_ADDITION = "&Message=Book successful addition in library!";
	private static final String URL_VIEW_BOOK="http://localhost:8080/WebTask/Controller?command=viewBook&id=+";
	private static final String MAIN_JSP = "WEB-INF/jsp/main.jsp";
	private static final String ERROR_JSP = "error.jsp";
	private Random random = new Random();
    
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 response.setContentType("text/html;charset=UTF-8");
		 final String Writer=request.getParameter("writer");
		 final String nameBook=request.getParameter("nameBook");
		 final String genre=request.getParameter("genre");
		 final String house=request.getParameter("house");
		 final String year =request.getParameter("year");
		 final Part filePart=request.getPart("file");
		   
		 File uploadetFile = null;
	
		 String pathImage;
		 String nameImage;
	
		//выбираем файлу имя пока не найдём свободное
		 do{
			 pathImage = ("C:/Users/Dima/git/Library-Project/WebTask/WebContent/resources/images/" + random.nextInt(1000)+".jpg");	
				uploadetFile = new File(pathImage);		
			}while(uploadetFile.exists());
		
			//создаём файл
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
		       
		        
		        
		        System.out.println(pathImage);
				
				 System.out.println( "pered fabricoi");
				ServiceFactory factory=ServiceFactory.getInstance();
				BookService bookService=factory.getBookService();
				
				Book book = null;
				String page = null;
				try {
					book = bookService.addBook( Writer,nameBook,pathImage,genre,house,year);
					 System.out.println(book.getId());
					if (book!=null)	{
						int i=book.getId();
						String url=URL_VIEW_BOOK+i;
						String url2=url+MESSAGE_SUCCESSFUL_ADDITION;
						 System.out.println( url);
						 
					response.sendRedirect(url);
						
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
		    }}}
	
	
        

    
		
			



