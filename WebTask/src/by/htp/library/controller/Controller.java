package by.htp.library.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import by.htp.library.command.Command;
import by.htp.library.dao.connection.pool.ConnectionPool;
import by.htp.library.dao.connection.pool.ConnectionPoolException;
import by.htp.library.dao.connection.pool.ConnectionPoolFactory;


@MultipartConfig
public class Controller extends HttpServlet {
	
	   private static final long serialVersionUID = -1852427791495732042L;
	
	   private static final CommandProvider PROVIDER=new CommandProvider();  
	   private static final String REQUEST_PARAMETR="command";  
		
	 
	    public Controller() {
	        super();
	    }

		@Override
		public void init(ServletConfig config) throws ServletException {
			
			super.init(config);
			ConnectionPoolFactory objectCPFactory = ConnectionPoolFactory.getInstance();
			ConnectionPool cp =objectCPFactory.getConnectionPool();
			
				try {
					cp.initPoolData();
				} catch (ConnectionPoolException e) {
						throw new CreatingConnectionPoolException(e);
					
				}
		}
		
		
		@Override
		protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
			super.service(arg0, arg1);
		}
		
		
		protected void doGet(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException {
			
			//String commandName = null;
		//	if(request.getParameter(REQUEST_PARAMETR) != null){
				String commandName = request.getParameter(REQUEST_PARAMETR);
				Command command = PROVIDER.getCommand(commandName);
				command.execute(request, response);	
			//}else{
			//processRequest(request,response);
			//}
		}

/*
		private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
			String i=request.getParameter("index");
			OutputStream outputStream=response.getOutputStream();
			try{
				byte [] imageContent=Files.readAllBytes(Paths.get(i));
			
				response.setContentType("image/jpg");
				outputStream.write(imageContent);
			
			}finally{
			outputStream.close();
			}
		}*/
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			//boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			//if (!isMultipart) {
				String commandName=request.getParameter(REQUEST_PARAMETR);
				Command command=PROVIDER.getCommand(commandName);
				command.execute(request, response);
		/*	}
			else{
				Command command=PROVIDER.getCommand("AddNewBook");
				command.execute(request, response);
			}*/
		}
		
		 
		public void destroy(){
			super.destroy();
			ConnectionPoolFactory objectCPFactory = ConnectionPoolFactory.getInstance();
			ConnectionPool cp =objectCPFactory.getConnectionPool();
			cp.dispose();
		}	
	
}
