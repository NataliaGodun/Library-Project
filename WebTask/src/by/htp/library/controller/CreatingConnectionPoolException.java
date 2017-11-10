package by.htp.library.controller;

public class CreatingConnectionPoolException extends RuntimeException {

	private static final long serialVersionUID = 3185938667870778580L;

	public CreatingConnectionPoolException() {
		super();
		}
	public CreatingConnectionPoolException(String message) {
		super(message);
		}
	public CreatingConnectionPoolException(Exception e) {
		super(e);
		}
	public CreatingConnectionPoolException(String message,Exception e) {
		super(message,e);
		}
}
