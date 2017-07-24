package Interface;

import BaseObjects.MazeDTO;
import BaseObjects.MoveCommand;

/**
 * @author XRC_7331 Interface for executing commands over robot in maze.
 */
public interface IMazeCommandExecutor {
	/**
	 * @param maze
	 *            The maze with the robot, over which will be executed the
	 *            command.
	 * @param command
	 *            The executed command
	 * @return The code of the executed command.
	 */
	public void ExecuteNext(final MazeDTO maze, final MoveCommand command);
}
