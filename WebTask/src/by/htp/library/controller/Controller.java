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
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.ibm.useful.http.FileData;
import com.ibm.useful.http.PostData;

import by.htp.library.command.Command;
import by.htp.library.dao.connection.pool.ConnectionPool;
import by.htp.library.dao.connection.pool.ConnectionPoolException;
import by.htp.library.dao.connection.pool.ConnectionPoolFactory;


@MultipartConfig
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private static final CommandProvider PROVIDER=new CommandProvider();  
    private static final String COMMAND="command";  
   
    private static final String SAVE_DIR = "resources/images";
    
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
			String description = request.getParameter("description");
			String data = request.getParameter("data");
			
			System.out.println("description="+description);
			System.out.println("data="+data);
			
			 String commandName=request.getParameter(COMMAND);
			  System.out.println(commandName);
			Command command=PROVIDER.getCommand(commandName);
			command.execute(request, response);
		}
		else{
		/// gets absolute path of the web application
	        String appPath = request.getServletContext().getRealPath("");
	        // constructs path of the directory to save uploaded file
	        String savePath = appPath + File.separator + SAVE_DIR;
	         
	        // creates the save directory if it does not exists
	        File fileSaveDir = new File(savePath);
	        if (!fileSaveDir.exists()) {
	            fileSaveDir.mkdir();
	        }
	         
	        for (Part part : request.getParts()) {
	            String fileName = extractFileName(part);
	            // refines the fileName in case it is an absolute path
	            fileName = new File(fileName).getName();
	            System.out.println( fileName);
	            part.write("C:/Users/Dima/git/Library-Project/WebTask/WebContent/resources/images/"+ fileName);
	           
	        }
	        request.setAttribute("message", "Upload has been done successfully!");
	        getServletContext().getRequestDispatcher("/message.jsp").forward(
	                request, response);
	    }
	}
	    /**
	     * Extracts file name from HTTP header content-disposition
	     */
	    private String extractFileName(Part part) {
	        String contentDisp = part.getHeader("content-disposition");
	        String[] items = contentDisp.split(";");
	        for (String s : items) {
	            if (s.trim().startsWith("filename")) {
	                return s.substring(s.indexOf("=") + 2, s.length()-1);
	            }
	        }
	        return "";
	    }
		
	
	public void destroy(){
		super.destroy();
		ConnectionPoolFactory ObjectCPFactory = ConnectionPoolFactory.getInstance();
		ConnectionPool cp =ObjectCPFactory.getConnectionPool();
		cp.dispose();
	}

	
	
}
