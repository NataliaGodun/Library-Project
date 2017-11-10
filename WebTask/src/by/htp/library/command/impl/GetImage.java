package by.htp.library.command.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.library.command.Command;

public class GetImage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

}
