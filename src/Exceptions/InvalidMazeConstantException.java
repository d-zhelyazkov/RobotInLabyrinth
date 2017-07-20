package Exceptions;

/**
 * Thrown when a maze's constant is bigger than maze's size parameters.
 * 
 * @author XRC_7331
 */
public class InvalidMazeConstantException extends RuntimeException {
	
	private static final long serialVersionUID = -1353522083531789379L;

	public InvalidMazeConstantException(String err)
	{
		super(err);
	}
}
