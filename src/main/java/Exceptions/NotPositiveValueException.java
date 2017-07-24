package Exceptions;

/**
 * Exception, thrown when number is not positive.
 * 
 * @author XRC_7331
 */
public class NotPositiveValueException extends RuntimeException {

	/**
	 * Exception's identifier.
	 * Auto generated.
	 */
	private static final long serialVersionUID = 8461359550347205061L;
	
	/**
	 * Exception's constructor.
	 * @param err The error message, send by it's source.
	 */
	public NotPositiveValueException(String err)
	{
		super(err);
	}

}
