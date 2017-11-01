package by.htp.library.service;

import java.util.ArrayList;

import by.htp.library.domain.Book;
import by.htp.library.service.exception.ServiceException;

public interface BookService {
ArrayList<Book> showBooks () throws ServiceException;

Book addBook( String writer,String  nameBook,String image, String genre,String house ) throws ServiceException;

Book viewBook(String id)throws ServiceException;

Book deleteBook(String nazvanie, String avtor) throws ServiceException;

Book addImageBook(String fileName) throws ServiceException;
}
