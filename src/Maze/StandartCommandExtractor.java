package Maze;

import BaseObjects.MazeDTO;
import BaseObjects.Point;
import BaseObjects.Directions.AbstractDirection;
import Exceptions.NotValidMoveDirectionException;
import Interface.ICommandExtractor;
import Interface.IDirFinder;

public class StandartCommandExtractor implements ICommandExtractor {
	private IDirFinder finder;

	public StandartCommandExtractor() {
		finder = new StandartDirFinder();
	}

	@Override
	public MoveCommandHandler Extract(MazeDTO maze) {
		final MoveCommandHandler commands = new MoveCommandHandler();
		Point currentPosition = maze.robot;

		while (true) {
			try {
				AbstractDirection direction = finder.GetNextDir(maze, currentPosition);
				commands.InsertCommand(direction);
				currentPosition = direction.TransformPoint(currentPosition);
			} catch (NotValidMoveDirectionException e) {
				break;
			}
		}

		return commands;
	}

}
