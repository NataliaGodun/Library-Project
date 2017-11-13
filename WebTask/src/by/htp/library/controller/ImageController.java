package by.htp.library.controller;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.library.command.Command;



@MultipartConfig
public class ImageController extends HttpServlet {

	private static final long serialVersionUID = 7764697338154809933L;
	
	private static final String REQUEST_PARAMETR="command"; 
	private static final String ADD_NEW_BOOK="AddNewBook";
	private static final CommandProvider PROVIDER=new CommandProvider();  
	
	
	public ImageController() {
        super();
        
    }
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		super.init(config);
		
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String commandName = request.getParameter(REQUEST_PARAMETR);
		Command command = PROVIDER.getCommand(commandName);
		command.execute(request, response);	
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Command command=PROVIDER.getCommand(ADD_NEW_BOOK);
		command.execute(request, response);
	}
	
	
	
	public void destroy(){
		super.destroy();
		
	}	

}
