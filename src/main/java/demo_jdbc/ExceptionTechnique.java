package demo_jdbc;

public class ExceptionTechnique extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2524099638698721506L;

	public ExceptionTechnique(String message) {
		super(message);
	}

	public ExceptionTechnique(Throwable cause) {
		super(cause);
	}

	public ExceptionTechnique(String message, Throwable cause) {
		super(message, cause);
	}

}
