package Interface;

import BaseObjects.MazeDTO;
import Maze.MoveCommandHandler;

/**
 * Util that traces robot steps to final in a maze.
 * 
 * @author XRC_7331
 *
 */
public interface ICommandExtractor {
	public MoveCommandHandler Extract(final MazeDTO maze);
}
