package by.htp.library.service;

import by.htp.library.domain.User;
import by.htp.library.service.exception.ServiceException;

/**
 * Ñhecks the parameters and passes control to the dao layer
 * 
 * @author Godun Natalia
 * @version 1.0
 */

public interface UserService {
	User authorization(String login, String password) throws ServiceException;

	User registration(String name, String login, String password) throws ServiceException;

	User editProfileName(String name, String login) throws ServiceException;

	User editProfilePassword(String password, String login) throws ServiceException;
}
