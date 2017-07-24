package Exceptions;

public class PathNotFoundInMazeException extends RuntimeException {

	private static final long serialVersionUID = 2613446981009416391L;

	public PathNotFoundInMazeException(String err) {
		super(err);
	}

}
