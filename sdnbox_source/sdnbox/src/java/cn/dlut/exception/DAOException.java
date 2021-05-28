package cn.dlut.exception;

public class DAOException extends ApplicationException {	
	private static final long serialVersionUID = 7348044819053998214L;
	
	/**
	 * Default Constructor
	 * @param msg
	 * @param e
	 */
	public DAOException(String msg, Exception e) {
		super(msg, e);
	}
	
	/**
	 * Default Constructor
	 * @param msg
	 */
	public DAOException(String msg) {
		super(msg);
	}
}
