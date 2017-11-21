package by.htp.library.controller;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.htp.library.command.Command;
import by.htp.library.dao.connection.pool.ConnectionPool;
import by.htp.library.dao.connection.pool.ConnectionPoolException;
import by.htp.library.dao.connection.pool.ConnectionPoolFactory;


public class Controller extends HttpServlet {
	
	   private static final long serialVersionUID = -1852427791495732042L;
	
	   private static final CommandProvider PROVIDER=new CommandProvider();  
	   private static final String REQUEST_PARAMETR="command"; 
	   private static final String MESSAGE_ERROR_CREATING_CONNECTION_POOL="Error creating connection pool";
		
	   private static final Logger LOGGER = LogManager.getRootLogger();
		
		
	 
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
					
					LOGGER.log(Level.ERROR, MESSAGE_ERROR_CREATING_CONNECTION_POOL, e);	
		
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
