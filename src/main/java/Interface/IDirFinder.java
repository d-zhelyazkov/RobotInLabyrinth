package Interface;

import BaseObjects.MazeDTO;
import BaseObjects.Point;
import BaseObjects.Directions.AbstractDirection;
import Exceptions.NotValidMoveDirectionException;

/**
 * Interface, which scans a MazeDTO and finds robots's next direction.
 * 
 * @author XRC_7331
 *
 */
public interface IDirFinder {
	/**
	 * The analyzing method.
	 * 
	 * @param maze
	 *            The analyzed DTO
	 * @param robot
	 *            Robots current position
	 * @return The direction.
	 * @throws NotValidMoveDirectionException
	 *             probably the maze was not solved.
	 */
	public AbstractDirection GetNextDir(final MazeDTO maze, final Point robot)
			throws NotValidMoveDirectionException;
}
