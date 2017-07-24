package Exceptions;

/**
 * Exception, thrown when IX is not positive or is beyond boundary.
 * 
 * @author XRC_7331
 */
public class InvalidIXException extends RuntimeException {

	/**
	 * Exception's identifier.
	 * Auto generated.
	 */
	private static final long serialVersionUID = -6255873245951492198L;
	
	/**
	 * Exception's constructor.
	 * @param err The error message, send by it's source.
	 */
	public InvalidIXException(String err)
	{
		super(err);
	}

}
