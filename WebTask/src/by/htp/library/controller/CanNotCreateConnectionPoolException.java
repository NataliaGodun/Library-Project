package by.htp.library.controller;

public class CanNotCreateConnectionPoolException extends RuntimeException {

	private static final long serialVersionUID = 2010757631380403956L;
	
	public CanNotCreateConnectionPoolException() {
		super();
		}
	public CanNotCreateConnectionPoolException(String message) {
		super(message);
		}
	public CanNotCreateConnectionPoolException(Exception e) {
		super(e);
		}
	public CanNotCreateConnectionPoolException(String message,Exception e) {
		super(message,e);
		}

}

