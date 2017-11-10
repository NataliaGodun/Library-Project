package by.htp.library.controller;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.htp.library.command.Command;
import by.htp.library.dao.connection.pool.ConnectionPool;
import by.htp.library.dao.connection.pool.ConnectionPoolException;
import by.htp.library.dao.connection.pool.ConnectionPoolFactory;


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
			
				String commandName = request.getParameter(REQUEST_PARAMETR);
				Command command = PROVIDER.getCommand(commandName);
				command.execute(request, response);	
		
		}

		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				String commandName=request.getParameter(REQUEST_PARAMETR);
				Command command=PROVIDER.getCommand(commandName);
				command.execute(request, response);
		
		}
		
		 
		public void destroy(){
			super.destroy();
			ConnectionPoolFactory objectCPFactory = ConnectionPoolFactory.getInstance();
			ConnectionPool cp =objectCPFactory.getConnectionPool();
			cp.dispose();
		}	
	
}
