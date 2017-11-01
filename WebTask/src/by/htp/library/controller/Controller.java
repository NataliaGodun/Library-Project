package by.htp.library.controller;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import by.htp.library.command.Command;
import by.htp.library.dao.connection.pool.ConnectionPool;
import by.htp.library.dao.connection.pool.ConnectionPoolException;
import by.htp.library.dao.connection.pool.ConnectionPoolFactory;
import by.htp.library.domain.Book;
import by.htp.library.service.BookService;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.service.factory.ServiceFactory;


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
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String commandName=request.getParameter(COMMAND);
		  
			Command command=PROVIDER.getCommand(commandName);
			command.execute(request, response);
			
		}
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if (!isMultipart) {
				String commandName=request.getParameter(COMMAND);
				Command command=PROVIDER.getCommand(commandName);
				command.execute(request, response);
			}
			else{
				String commandName=("AddNewBook");
				Command command=PROVIDER.getCommand(commandName);
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
