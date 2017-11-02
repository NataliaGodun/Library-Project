package by.htp.library.command.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.library.command.Command;

public class ShowImage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
			

					byte [] imageContent=Files.readAllBytes(Paths.get("C:/Users/Dima/git/Library-Project/WebTask/WebContent/resources/images/737.jpg)"));
					
					response.setContentType("image/png");
					
			         response.setContentLength(imageContent.length);
			         response.getOutputStream().write(imageContent);
				

	
	}
}
