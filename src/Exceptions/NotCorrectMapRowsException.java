package Exceptions;

public class NotCorrectMapRowsException extends RuntimeException {

	private static final long serialVersionUID = -2165907985272970746L;

	public NotCorrectMapRowsException(String err)
	{
		super(err);
	}
}
