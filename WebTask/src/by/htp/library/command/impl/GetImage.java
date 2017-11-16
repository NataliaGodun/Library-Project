package by.htp.library.command.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.htp.library.command.Command;


public class GetImage implements Command {
	private static final String INDEX= "index";
	private static final String CONTENT_TYPE_IMAGE_JPG= "image/jpg";
	private static final String MESSAGE_LOGGER_INFO = "The exception has occurred at GetImage";

	private static final Logger LOGGER = LogManager.getRootLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String i=request.getParameter(INDEX);
		OutputStream outputStream=response.getOutputStream();
		try{
			byte [] imageContent=Files.readAllBytes(Paths.get(i));
		
			response.setContentType(CONTENT_TYPE_IMAGE_JPG);
			outputStream.write(imageContent);
			
		}catch(IOException e){
				
			LOGGER.info(MESSAGE_LOGGER_INFO );
			
		}finally{
		outputStream.close();
		}

	}

}
