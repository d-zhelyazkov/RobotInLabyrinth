package Exceptions;

public class NotValidMazeCharacterException extends RuntimeException {

	
	private static final long serialVersionUID = -5689189852465257723L;

	public NotValidMazeCharacterException(String err)
	{
		super(err);
	}
}
