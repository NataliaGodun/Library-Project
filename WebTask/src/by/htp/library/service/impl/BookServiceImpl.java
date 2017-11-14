package by.htp.library.service.impl;

import java.util.ArrayList;

import by.htp.library.dao.BookDAO;
import by.htp.library.dao.exception.DAOException;
import by.htp.library.dao.factory.DAOFactory;
import by.htp.library.domain.Book;
import by.htp.library.service.BookService;
import by.htp.library.service.exception.ServiceException;

public class BookServiceImpl implements BookService {
	
	public ArrayList<Book> showBooks () throws ServiceException{
	
		DAOFactory daoObjectFactory=DAOFactory.getInstance();
		BookDAO bookDAO=daoObjectFactory.getBookDAO();
		ArrayList <Book> List =null;
		try {
		  List=bookDAO.showBook();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return List;	
		
	}

	@Override
	public Book addBook( Book book ) throws ServiceException {
		
		ValidationBook.validateBook(book);
		
		DAOFactory daoObjectFactory=DAOFactory.getInstance();
		BookDAO bookDAO=daoObjectFactory.getBookDAO();
		try {
			return bookDAO.addBook(book);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}	
	}

	@Override
	public Book viewBook(int id) throws ServiceException {
		
		ValidationBook.validateBook(id);
		
		DAOFactory daoObjectFactory=DAOFactory.getInstance();
		BookDAO bookDAO=daoObjectFactory.getBookDAO();
		try {
			return bookDAO.viewBook(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}	
	}

	@Override
	public Book deleteBook(int id) throws ServiceException {
		
		ValidationBook.validateBook(id);
		
		DAOFactory daoObjectFactory=DAOFactory.getInstance();
		BookDAO bookDAO=daoObjectFactory.getBookDAO();
		try {
			return bookDAO.deleteBook(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}	
	}

	@Override
	public Book searchBook(String nameBook) throws ServiceException {
		
		ValidationBook.validateBook(nameBook);
			
		DAOFactory daoObjectFactory=DAOFactory.getInstance();
		BookDAO bookDAO=daoObjectFactory.getBookDAO();
		try {
			return bookDAO.searchBook(nameBook);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}	
	}
}
