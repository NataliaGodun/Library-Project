package by.htp.library.service;

import java.util.ArrayList;

import by.htp.library.domain.Book;
import by.htp.library.service.exception.ServiceException;

public interface BookService {
ArrayList<Book> showBooks () throws ServiceException;

Book addBook( String writer,String  nameBook,String pathImage, String genre,String house,String year ) throws ServiceException;

Book viewBook(int id)throws ServiceException;

Book deleteBook(int id) throws ServiceException;

Book searchBook(String nameBook)throws ServiceException;

}
