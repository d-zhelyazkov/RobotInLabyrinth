package Maze;

import BaseObjects.MazeDTO;
import BaseObjects.Point;
import BaseObjects.Directions.AbstractDirection;
import BaseObjects.Directions.DownDir;
import BaseObjects.Directions.LeftDir;
import BaseObjects.Directions.RightDir;
import BaseObjects.Directions.UpDir;
import Exceptions.NotValidMoveDirectionException;
import Interface.IDirFinder;

public class StandartDirFinder implements IDirFinder {

	@Override
	public AbstractDirection GetNextDir(final MazeDTO maze, final Point p)
			throws NotValidMoveDirectionException {
		char[][] map = maze.mazeMap;
		int k = maze.k - 1;

		AbstractDirection dir = new DownDir();
		if (map[p.y][p.x] != dir.symbol || map[p.y][p.x + k] != dir.symbol) {
			dir = new LeftDir();
			if (map[p.y][p.x + k] != dir.symbol
					|| map[p.y + k][p.x + k] != dir.symbol) {
				dir = new UpDir();
				if (map[p.y + k][p.x + k] != dir.symbol
						|| map[p.y + k][p.x] != dir.symbol) {
					dir = new RightDir();
					if (map[p.y + k][p.x] != dir.symbol
							|| map[p.y][p.x] != dir.symbol) {
						throw new NotValidMoveDirectionException(
								"The robot stands on unmaerked points!");
					}
				}
			}
		}

		return dir;
	}

}
