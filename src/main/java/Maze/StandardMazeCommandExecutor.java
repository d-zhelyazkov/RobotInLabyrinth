package Maze;

import BaseObjects.MazeDTO;
import BaseObjects.MoveCommand;
import Interface.IMazeCommandExecutor;

/**
 * @author XRC_7331 Standard executor class , working with char maze and char
 *         direction mapping.
 */
public class StandardMazeCommandExecutor implements IMazeCommandExecutor {

	@Override
	public void ExecuteNext(final MazeDTO maze, final MoveCommand command) {
		maze.robot = command.TransformPoint(maze.robot);
	}
}
