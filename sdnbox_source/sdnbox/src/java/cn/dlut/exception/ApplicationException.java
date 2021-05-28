package cn.dlut.exception;


public class ApplicationException extends Exception {

	/**
	 * Default serial version UID
	 */
	private static final long serialVersionUID = 1L;

	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApplicationException(String message) {
		super(message);
	}

	public ApplicationException(Throwable cause) {
		super(cause);
	}

	public ApplicationException() {
		super();
	}
}
