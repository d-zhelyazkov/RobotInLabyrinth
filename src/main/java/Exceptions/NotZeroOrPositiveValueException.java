package Exceptions;


/**
 * Exception, thrown when number is negative.
 * 
 * @author XRC_7331
 */
public class NotZeroOrPositiveValueException extends RuntimeException {

	/**
	 * Exception's identifier.
	 * Auto generated.
	 */
	private static final long serialVersionUID = 3683381513513606702L;

	/**
	 * Exception's constructor.
	 * @param err The error message, send by it's source.
	 */
	public NotZeroOrPositiveValueException(String err)
	{
		super(err);
	}
}
