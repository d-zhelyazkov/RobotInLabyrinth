package Exceptions;


/**
 * Thrown when a maze has invalid parameter.
 * @author XRC_7331
 */
public class InvalidMazeParameterException extends RuntimeException {

	
	private static final long serialVersionUID = 4509447791820693241L;
	
	public InvalidMazeParameterException(Exception e)
	{
		super(e);
	}

}
