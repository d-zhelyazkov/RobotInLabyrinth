package Interface;

import BaseObjects.MazeDTO;
import Exceptions.PathNotFoundInMazeException;
import Maze.SolvingResult;

/**
 * Interface, which finds way from a robot to end.
 * 
 * @author XRC_7331
 *
 */
public interface IMazeSolver {
	/**
	 * @param maze
	 *            The maze where a path will be searched.
	 * @throws PathNotFoundInMazeException
	 */
	public SolvingResult Solve(final MazeDTO maze)
			throws PathNotFoundInMazeException;
}
