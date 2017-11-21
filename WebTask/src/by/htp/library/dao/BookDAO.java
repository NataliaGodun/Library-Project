package by.htp.library.dao;

import java.util.ArrayList;

import by.htp.library.dao.exception.DAOException;
import by.htp.library.domain.Book;

/**
 * @author Godun Natalia
 * @version 1.0
 */

public interface BookDAO {
	/**
	 * return all books
	 */
	ArrayList<Book> showBook() throws DAOException;

	/**
	 * add the book to database
	 * @param book
	 */
	Book addBook(Book book) throws DAOException;

	/**
	 * check the book from database
	 * @param id
	 */
	Book viewBook(int id) throws DAOException;


	/**
	 * delete the book from database
	 * @param id
	 */
	Book deleteBook(int id) throws DAOException;

	/**
	 * check the book from database
	 * @param nameBook
	 */
	Book searchBook(String nameBook) throws DAOException;

}