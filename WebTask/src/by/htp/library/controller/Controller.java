package by.htp.library.controller;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
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
import by.htp.library.domain.Book;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;


@MultipartConfig
public class Controller extends HttpServlet {
	
	private static final long serialVersionUID = -1852427791495732042L;
	
	 private static final CommandProvider PROVIDER=new CommandProvider();  
	    private static final String COMMAND="command";  
		
	 
	    public Controller() {
	        super();
	    }

		@Override
		public void init(ServletConfig config) throws ServletException {
			
			super.init(config);
			ConnectionPoolFactory ObjectCPFactory = ConnectionPoolFactory.getInstance();
			ConnectionPool cp =ObjectCPFactory.getConnectionPool();
			
				try {
					cp.initPoolData();
				} catch (ConnectionPoolException e) {
					throw new CanNotCreateSource(e);
				}
			
			
		}
		@Override
		protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
			super.service(arg0, arg1);
		}
		
		protected void doGet(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException {
			String commandName = null;
			if(request.getParameter("command") != null){
				commandName = request.getParameter("command");
				System.out.println(commandName);
				Command command = PROVIDER.getCommand(commandName);
				command.execute(request, response);	
			}else{
			processRequest(request,response);
			}
		}


		private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//	String i="C:/Users/Dima/git/Library-Project/WebTask/WebContent/resources/images/374.jpg";
			String i=request.getParameter("index");
			OutputStream outputStream=response.getOutputStream();
			try{
			byte [] imageContent=Files.readAllBytes(Paths.get(i));
			
			response.setContentType("image/jpg");
			outputStream.write(imageContent);
			
		}finally{
			outputStream.close();
		}
		}
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if (!isMultipart) {
				String commandName=request.getParameter(COMMAND);
				Command command=PROVIDER.getCommand(commandName);
				command.execute(request, response);
			}
			else{
				Command command=PROVIDER.getCommand("AddNewBook");
				command.execute(request, response);
			}
		}
		
	

		 
		public void destroy(){
			super.destroy();
			ConnectionPoolFactory ObjectCPFactory = ConnectionPoolFactory.getInstance();
			ConnectionPool cp =ObjectCPFactory.getConnectionPool();
			cp.dispose();
		}

		
	
}
