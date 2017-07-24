package Exceptions;

public class InvalidMazeException extends RuntimeException {

	private static final long serialVersionUID = -3958964505912431852L;

	public InvalidMazeException(Exception e) {
		super(e);
	}

}
