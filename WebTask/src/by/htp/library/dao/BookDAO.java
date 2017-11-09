package by.htp.library.dao;

import java.util.ArrayList;

import by.htp.library.dao.exception.DAOException;
import by.htp.library.domain.Book;

public interface BookDAO {
ArrayList<Book> showBook () throws DAOException;

Book addBook( Book book ) throws DAOException;

Book viewBook(int id) throws DAOException;

Book deleteBook(int id)throws DAOException;

Book searchBook(String nameBook)throws DAOException;

}