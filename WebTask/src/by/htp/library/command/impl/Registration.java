package by.htp.library.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.library.command.Command;
import by.htp.library.domain.User;

import by.htp.library.service.UserService;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.service.factory.ServiceFactory;

public class Registration implements Command {
	private static final String LOGIN= "login";
	private static final String PASSWORD = "password";
	private static final String USER = "user";
	private static final String ERROR_MESSAGE= "errorMessage";
	private static final String MESSAGE_LOGIN_EXISTS = "The user with such login already exists";
	private static final String MESSAGE_ABOUT_PROBLEM = "Sorry,technical problem";
	private static final String MAIN_JSP = "WEB-INF/jsp/main.jsp";
	private static final String INDEX_JSP = "index.jsp";
	private static final String NAME_USER = "name";
	private static final String ROLE= "role";
	private static final String NAME_USERS= "name";
	private static final String URL_VIEW_ALL_BOOK_WITH_INFO="http://localhost:8080/WebTask/Controller?command=VIEWALLBOOKS&message=The user with such login already exists";
	private static final String URL_VIEW_ALL_BOOK_WITH_ERROR="http://localhost:8080/WebTask/Controller?command=VIEWALLBOOKS&errorMessage=Sorry,technical problem";
	private static final String MESSAGE_LOGGER_INFO="Wrong registration ";
	
	private static final Logger LOGGER = LogManager.getRootLogger();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
					
		String name=request.getParameter( NAME_USER);
		String login=request.getParameter(LOGIN);
		String password=request.getParameter(PASSWORD);
			
		ServiceFactory factory=ServiceFactory.getInstance();
		UserService userService=factory.getUserService();
				
		String page;
		User user = null;
		try {
			user = userService.registration(name, login, password);
			if (user!=null){
				String role=user.getRole();
						
				HttpSession session=request.getSession(true);
						
				session.setAttribute(NAME_USERS, name);
				session.setAttribute(ROLE, role);
				session.setAttribute(LOGIN,login);
				request.setAttribute(USER , user);
						
				page=MAIN_JSP;
				RequestDispatcher dispatcher=request.getRequestDispatcher(page);
				
				dispatcher.forward(request, response);
						
			}else{
				response.sendRedirect(URL_VIEW_ALL_BOOK_WITH_INFO);
			}
		} catch (ServiceException e) {
			
			LOGGER.info(MESSAGE_LOGGER_INFO);
			
			response.sendRedirect(URL_VIEW_ALL_BOOK_WITH_ERROR);
		}
						
	}

}


