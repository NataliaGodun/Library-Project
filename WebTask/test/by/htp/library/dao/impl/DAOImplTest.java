package by.htp.library.dao.impl;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import by.htp.library.dao.connection.pool.ConnectionPool;
import by.htp.library.dao.connection.pool.ConnectionPoolException;
import by.htp.library.dao.connection.pool.ConnectionPoolFactory;
import by.htp.library.dao.exception.DAOException;
import by.htp.library.domain.Book;

public class DAOImplTest {
	
	ConnectionPoolFactory objectCPFactory = ConnectionPoolFactory.getInstance();
	ConnectionPool cp =objectCPFactory.getConnectionPool();
	
	@Before
	public void initConnectionPool() throws ConnectionPoolException{
		cp.initPoolData();
	}
	
	@After
	public void destroyConnectionPool(){
		cp.dispose();
	}
	
	@Test
	public void testAddBook() throws  DAOException{
		
		Book book=new Book(0,"test","test","test","test","test","test");
		SQLBookDAO sqlBookDAO=new SQLBookDAO();
		Book bookTest=sqlBookDAO.addBook(book);
		int id=bookTest.getId();
		book.setId(id);
		
		Book expected=book;
		Book actual=bookTest;
		assertEquals(expected,actual);	
	}
	
	@Test
	public void testViewBook() throws  DAOException{
		int id=146;
		Book book=new Book(id,"test","test","test","test","test","test");
		SQLBookDAO sqlBookDAO=new SQLBookDAO();
		Book bookTest=sqlBookDAO.viewBook(id);
		
		Book expected=book;
		Book actual=bookTest;
		assertEquals(expected,actual);
	}
	
	@Test
	public void testDeleteBook() throws  DAOException{
		int id=147;
		Book book=null;
		SQLBookDAO sqlBookDAO=new SQLBookDAO();
		Book bookTest=sqlBookDAO.deleteBook(id);
		
		Book expected=book;
		Book actual=bookTest;
		assertEquals(expected,actual);
	}
	
	@Test
	public void testAddDleteBook() throws  DAOException{
		
		Book book=new Book(0,"test","test","test","test","test","test");
		SQLBookDAO sqlBookDAO=new SQLBookDAO();
		Book bookTest=sqlBookDAO.addBook(book);
		int id=bookTest.getId();
		bookTest=sqlBookDAO.deleteBook(id);
		Book bookResult=null;
		
		Book expected=bookResult;
		Book actual=bookTest;
		assertEquals(expected,actual);		
	}
	
	@Test
	public void testSearchBook() throws  DAOException{
		int id=4;
		Book book=new Book(id,"Collen McCullought","The thotn birds","C:/Users/Dima/git/Library-Project/WebTask/WebContent/resources/images/862.jpg","novel","HarperCollins","1977");
		SQLBookDAO sqlBookDAO=new SQLBookDAO();
		Book bookTest=sqlBookDAO.viewBook(id);
		
		Book expected=book;
		Book actual=bookTest;
		assertEquals(expected,actual);
	}
	
}
