package by.epam.jwd.service;

public class ServiceException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message, Exception cause) {
		super(message, cause);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Exception cause) {
		super(cause);
	}
	
}
