package by.htp.library.dao.connection.pool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public interface ConnectionPool {
	public void initPoolData() throws ConnectionPoolException;

	public void dispose();

	public Connection takeConnection() throws ConnectionPoolException;

	public void closeConnection(Connection con, Statement st, ResultSet rs);

	public void closeConnection(Connection con, Statement st);

	public void removeConnection() throws ConnectionPoolException;
}
