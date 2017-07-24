package Exceptions;

public class NotValidMoveDirectionException extends Exception {

	private static final long serialVersionUID = -9120348702533922837L;

	public NotValidMoveDirectionException(String err)
	{
		super(err);
	}
}
