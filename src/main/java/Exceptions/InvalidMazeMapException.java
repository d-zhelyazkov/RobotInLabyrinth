package Exceptions;

/**
 * Exception describing an problem with a maze's map validation.
 * Some mismatching map parameters or map symbols might have occur.
 * 
 * @author XRC_7331
 */
public class InvalidMazeMapException extends RuntimeException {

	
	private static final long serialVersionUID = 4768106579685393779L;
	
	public InvalidMazeMapException(Exception e)
	{
		super(e);
	}

}
