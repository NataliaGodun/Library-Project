package by.htp.library.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import by.htp.library.dao.BookDAO;
import by.htp.library.dao.connection.pool.ConnectionPool;
import by.htp.library.dao.connection.pool.ConnectionPoolException;
import by.htp.library.dao.connection.pool.ConnectionPoolFactory;
import by.htp.library.dao.exception.DAOException;
import by.htp.library.domain.Book;



public class SQLBookDAO implements BookDAO {
	private static final String SELECT_ALL_BOOK = "SELECT * FROM BOOKS WHERE STATUS='EXIST'";
	private static final String ADD_BOOK = "INSERT INTO BOOKS (WRITER,NAMEBOOK,GENRE,PUBLISHINGHOUSE,PUBLISHINGYEAR,STATUS) VALUES(?,?,?,?,?,'EXIST')";
	private static final String BOOK_SELECT = "SELECT * FROM BOOKS WHERE WRITER=? AND NAMEBOOK=? AND STATUS='EXIST'";
	private static final String SELECT_BOOK_ID = "SELECT * FROM BOOKS WHERE ID=? AND STATUS='EXIST' ";
	private static final String DELETE_BOOK_NAME_WRITER = "UPDATE BOOK SET STATUS='DELETE' WHERE NAME=? AND NAZVANIE=?";
	private static final int FIRST= 1;
	private static final int SECOND = 2;
	private static final int THIRD = 3;
	private static final int FOURTH = 4;
	private static final int FIFTH = 5;
	private static final int SIXTH = 6;
	private static final int SEVENTH = 7;

	@Override
	public ArrayList<Book> showBook() throws DAOException{
		Connection con = null;
		ResultSet rs = null;
		Book book=null;
		
		ArrayList <Book> List = new ArrayList<Book>();
		
		ConnectionPoolFactory ObjectCPFactory = ConnectionPoolFactory.getInstance();
		ConnectionPool cp = ObjectCPFactory.getConnectionPool();

		try {
			
			con = cp.takeConnection();
			PreparedStatement ps = con.prepareStatement(SELECT_ALL_BOOK);
			rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(FIRST);
				String writer = rs.getString(SECOND);
				String nameBook = rs.getString(THIRD);
				String image = rs.getString(FOURTH);
				String genre = rs.getString(FIFTH);
				String house = rs.getString(SIXTH);
				String year = rs.getString(SEVENTH);
				
				book = new Book(id,writer, nameBook, image,genre,house,year);
				List.add(book);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		finally{
			try {
				cp.removeConnection();
			} catch (ConnectionPoolException e) {
				// Log.ERROR
				e.printStackTrace();
			}
		}
		
		return List;
		
		
	}

	@Override
	public Book addBook( String writer,String  nameBook, String genre,String house,String year ) throws DAOException {
		System.out.println( "v dao");
		Connection con = null;
		ResultSet rs = null;
		Book book= null;
		ConnectionPoolFactory ObjectCPFactory = ConnectionPoolFactory.getInstance();
		ConnectionPool cp = ObjectCPFactory.getConnectionPool();
		System.out.println( "pered try");
		try {
			con = cp.takeConnection();
			
			PreparedStatement ps = con.prepareStatement(ADD_BOOK);
	
			ps.setString(FIRST, writer);
			ps.setString(SECOND,  nameBook);
			ps.setString(THIRD,genre);
			ps.setString(FOURTH,house);
			ps.setString(FIFTH,year);
	
			
			ps.executeUpdate();
			System.out.println( "posle update");

			ps = con.prepareStatement(BOOK_SELECT);
			ps.setString(FIRST, writer);
			ps.setString(SECOND, nameBook );
			rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(FIRST);
				String Writer = rs.getString(SECOND);
				String NameBook = rs.getString(THIRD);
				String Image = rs.getString(FOURTH);
				String Genre = rs.getString(FIFTH);
				String House = rs.getString(SIXTH);
				String Year = rs.getString(SEVENTH);
				
				
				book = new Book(id,Writer, NameBook, Image,Genre,House,Year);
			}
			

		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		finally{
			try {
				cp.removeConnection();
			} catch (ConnectionPoolException e) {
				// Log.ERROR
				e.printStackTrace();
			}
		}
		return book;
	}

	@Override
	public Book viewBook(String id) throws DAOException {
		
		Connection con = null;
		ResultSet rs = null;
		Book book= null;

		ConnectionPoolFactory ObjectCPFactory = ConnectionPoolFactory.getInstance();
		ConnectionPool cp = ObjectCPFactory.getConnectionPool();
		try {

			con = cp.takeConnection();

			PreparedStatement ps = con.prepareStatement(SELECT_BOOK_ID);

			ps.setString(FIRST, id);
			
			
			rs = ps.executeQuery();
			while (rs.next()) {
				
				int idDB = rs.getInt(FIRST);
				String writer = rs.getString(SECOND);
				String nameBook = rs.getString(THIRD);
				String image = rs.getString(FOURTH);
				String genre = rs.getString(FIFTH);
				String house = rs.getString(SIXTH);
				String year = rs.getString(SEVENTH);
				
				book = new Book(idDB,writer, nameBook, image,genre,house,year);
			}
			

		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		finally{
			try {
				cp.removeConnection();
			} catch (ConnectionPoolException e) {
				// Log.ERROR
				e.printStackTrace();
			}
		}
		return book;
	}

	@Override
	public Book deleteBook(String  nameBook, String writer) throws DAOException {
		Connection con = null;
		ResultSet rs = null;
		Book book= null;
		/*
		ConnectionPoolFactory ObjectCPFactory = ConnectionPoolFactory.getInstance();
		ConnectionPool cp = ObjectCPFactory.getConnectionPool();
		try {
			con = cp.takeConnection();
			
			PreparedStatement ps = con.prepareStatement(DELETE_BOOK_NAME_WRITER );

			ps.setString(FIRST, writer);
			ps.setString(SECOND, nameBook);
			
			ps.executeUpdate();

			ps = con.prepareStatement(BOOK_SELECT);
			ps.setString(FIRST, writer);
			ps.setString(SECOND, nameBook);
			rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(FIRST);
				String  NameBook = rs.getString(SECOND);
				String Writer = rs.getString(THIRD);
				
				book= new Book(id,  NameBook , Writer);
			}

		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		finally{
			try {
				cp.removeConnection();
			} catch (ConnectionPoolException e) {
				// Log.ERROR
				e.printStackTrace();
			}
		}*/return book;
	}

	@Override
	public Book addImageBook(String image) throws DAOException {
		System.out.println( "v dao");
		Connection con = null;
		ResultSet rs = null;
		Book book= null;
		ConnectionPoolFactory ObjectCPFactory = ConnectionPoolFactory.getInstance();
		ConnectionPool cp = ObjectCPFactory.getConnectionPool();
		System.out.println( "pered try");
		try {
			con = cp.takeConnection();
			
			PreparedStatement ps = con.prepareStatement(ADD_BOOK);
	
			ps.setString(FIRST, image);
			ps.setString(SECOND,  nameBook);
			ps.setString(THIRD,genre);
			ps.setString(FOURTH,house);
			ps.setString(FIFTH,year);
	
			
			ps.executeUpdate();
			System.out.println( "posle update");

			ps = con.prepareStatement(BOOK_SELECT);
			ps.setString(FIRST, writer);
			ps.setString(SECOND, nameBook );
			rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(FIRST);
				String Writer = rs.getString(SECOND);
				String NameBook = rs.getString(THIRD);
				String Image = rs.getString(FOURTH);
				String Genre = rs.getString(FIFTH);
				String House = rs.getString(SIXTH);
				String Year = rs.getString(SEVENTH);
			
				
				book = new Book(id,Writer, NameBook, Image,Genre,House,Year);
			}
			

		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		finally{
			try {
				cp.removeConnection();
			} catch (ConnectionPoolException e) {
				// Log.ERROR
				e.printStackTrace();
			}
		}
		return book;
	}

}
