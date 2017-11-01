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
		private Random random = new Random();
	    
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
				 response.setContentType("text/html;charset=UTF-8");
				 final String path = request.getParameter("destination");
				    System.out.println(path);
				    final Part filePart = request.getPart("file");
				    final String fileName = getFileName(filePart);
				 File uploadetFile = null;
				 String path1;
					//выбираем файлу имя пока не найдём свободное
					do{
						 path1 = ("d:/ddd/" +  random.nextInt()+".txt");					
						System.out.println(path1);
						uploadetFile = new File(path1);		
					}while(uploadetFile.exists());
					
					//создаём файл
					uploadetFile.createNewFile(); 
				 
				 
				 
				 
                    // System.out.println(random.nextInt());
				    // Create path components to save the file
				   
String appPath=request.getServletContext().getRealPath("");
String savePath=appPath+ File.separator+"resources/images";
				    OutputStream out = null;
				    InputStream filecontent = null;
				    final PrintWriter writer = response.getWriter();

				    try {
				        out = new FileOutputStream(new File(path1));
				        filecontent = filePart.getInputStream();

				        int read = 0;
				        final byte[] bytes = new byte[1024];

				        while ((read = filecontent.read(bytes)) != -1) {
				            out.write(bytes, 0, read);
				        }
				        writer.println("New file " + fileName + " created at " + path);
				       
				    } catch (FileNotFoundException fne) {
				        writer.println("You either did not specify a file to upload or are "
				                + "trying to upload a file to a protected or nonexistent "
				                + "location.");
				        writer.println("<br/> ERROR: " + fne.getMessage());

				        
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
		}
				private String getFileName(final Part part) {
				    final String partHeader = part.getHeader("content-disposition");
				  
				    for (String content : part.getHeader("content-disposition").split(";")) {
				        if (content.trim().startsWith("filename")) {
				            return content.substring(
				                    content.indexOf('=') + 1).trim().replace("\"", "");
				        }
				    }
				    return null;
				}
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
