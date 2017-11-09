package by.htp.library.service.impl;

import java.util.ArrayList;

import by.htp.library.dao.BookDAO;
import by.htp.library.dao.exception.DAOException;
import by.htp.library.dao.factory.DAOFactory;
import by.htp.library.domain.Book;
import by.htp.library.service.BookService;
import by.htp.library.service.exception.ServiceException;

public class BookServiceImpl implements BookService {
	private static final String MESSAGE_WRONG_NAME = "Incorrect name of the book";
	private static final String MESSAGE_WRONG_WRITER= "Incorrect writer";
	private static final String MESSAGE_WRONG_ID= "This book is not available";
	private static final String MESSAGE_WRONG_NAMEBOOK = null;
	
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
		if (book.getNameBook()==null){
			throw new ServiceException( MESSAGE_WRONG_NAME  );
		}
		if (book.getWriter()==null){
			throw new ServiceException( MESSAGE_WRONG_WRITER );
		}
		
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
		if (id<0){
			throw new ServiceException(MESSAGE_WRONG_ID );	
		}
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
		if (id<0){
			throw new ServiceException( MESSAGE_WRONG_NAME  );
		}
		
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
		if (nameBook==null||nameBook.isEmpty()){
			throw new ServiceException(MESSAGE_WRONG_NAMEBOOK );	
		}
			DAOFactory daoObjectFactory=DAOFactory.getInstance();
			BookDAO bookDAO=daoObjectFactory.getBookDAO();
			try {
				return bookDAO.searchBook(nameBook);
			} catch (DAOException e) {
				throw new ServiceException(e);
			}	
	}
}
