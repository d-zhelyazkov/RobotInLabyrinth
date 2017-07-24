package Maze;

import java.util.ArrayDeque;
import java.util.Queue;

import BaseObjects.LineParams;
import BaseObjects.MazeDTO;
import BaseObjects.Point;
import BaseObjects.Directions.AbstractDirection;
import BaseObjects.Directions.DownDir;
import BaseObjects.Directions.UpDir;
import Exceptions.NotZeroOrPositiveValueException;
import Exceptions.PathNotFoundInMazeException;
import Interface.IMazeSolver;
import UtilityClasses.MatricMapper;

/**
 * @author XRC_7331 Searches the most shortest path in a maze based on
 *         algorithm, imitating gas spreading.
 */
public final class GasolisationMazeSolver implements IMazeSolver {

	private int[][][] IXMapping;

	private int K;
	private char[][] map;
	private int W;
	private int H;
	private long markedElements;

	private Point begining;

	/**
	 * @param dirMapping
	 *            The directions mapping.
	 */
	public GasolisationMazeSolver() {
		InitIXMapping();
		markedElements = 0;
	}

	private void InitIXMapping() {
		IXMapping = new int[4][4][2];

		IXMapping[0][0][0] = 0;
		IXMapping[0][0][1] = -1;
		IXMapping[0][3][0] = -1;
		IXMapping[0][3][1] = 0;
		IXMapping[0][1][0] = 0;
		IXMapping[0][1][1] = 0;

		IXMapping[1][1][0] = 1;
		IXMapping[1][1][1] = 0;
		IXMapping[1][0][0] = 0;
		IXMapping[1][0][1] = -1;
		IXMapping[1][2][0] = 0;
		IXMapping[1][2][1] = 0;

		IXMapping[2][2][0] = 0;
		IXMapping[2][2][1] = 1;
		IXMapping[2][1][0] = 0;
		IXMapping[2][1][1] = 1;
		IXMapping[2][3][0] = -1;
		IXMapping[2][3][1] = +1;

		IXMapping[3][3][0] = -1;
		IXMapping[3][3][1] = 0;
		IXMapping[3][2][0] = 0;
		IXMapping[3][2][1] = 0;
		IXMapping[3][0][0] = 0;
		IXMapping[3][0][1] = -1;
	}

	private void CompleteMapping() {
		if (K == 0) {
			return;
		}

		IXMapping[0][1][0] = K;

		IXMapping[1][0][0] = -K;
		IXMapping[1][2][0] = -K;
		IXMapping[1][2][1] = K;

		IXMapping[2][1][0] += K;
		IXMapping[2][1][1] = -K + 1;
		IXMapping[2][3][1] = -K + 1;

		IXMapping[3][2][1] = K;
	}

	private Queue<LineParams> EndLines(final Point p) {
		Queue<LineParams> lines = new ArrayDeque<LineParams>();
		lines.clear();
		lines.add(new LineParams(p, new DownDir(), 0));
		lines.add(new LineParams(p.x, p.y + K - 1, new UpDir(), 0));
		return lines;
	}

	private Queue<LineParams> ChildsLineParams(final LineParams parrent) {
		final AbstractDirection parrentDir = parrent.dir;
		final AbstractDirection[] neighbourDirs = parrentDir.DirectionTree();

		Queue<LineParams> childs = new ArrayDeque<LineParams>();
		LineParams currentChild = null;
		int[] currentDiffs;
		for (int i = 0; i < neighbourDirs.length; i++) {
			currentChild = new LineParams(parrent.p, neighbourDirs[i],
					parrent.distanceToFinal + 1);
			currentDiffs = IXMapping[parrentDir.id][currentChild.dir.id];
			currentChild.p.x += currentDiffs[0];
			currentChild.p.y += currentDiffs[1];

			try {
				currentChild.p.Check();
			} catch (NotZeroOrPositiveValueException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				continue;
			}

			if (currentChild.p.x < W && currentChild.p.y < H) {
				childs.add(currentChild);

			}
		}

		return childs;
	}

	private int ProcessChildLine(final LineParams child) {
		AbstractDirection dir = child.dir;
		final char dirCh = dir.symbol;
		boolean isOdd = (dir.id % 2 == 0);
		Point p = new Point(child.p);
		int sameDots = 0;
		char currentDot = ' ';
		int robotDotsCount = 0;
		for (int i = 0; i < K; i++) {
			currentDot = map[p.y][p.x];
			if (currentDot != ' ') {
				if (currentDot == dirCh)
					sameDots++;
				else if (currentDot == '*')
					robotDotsCount++;
				else
					return -1;
			}
			if (isOdd)
				p.x++;
			else
				p.y++;
		}

		if (sameDots == K)
			return -1;

		p = new Point(child.p);
		for (int i = 0; i < K; i++) {
			map[p.y][p.x] = dirCh;

			if (isOdd)
				p.x++;
			else
				p.y++;

		}

		return robotDotsCount;
	}

	private long ProcessBeginLines(LineParams currentLine, int processingResult) {
		int remainingDots = K * K - processingResult;
		Queue<LineParams> currentChilds;
		LineParams currentChildLine;
		long markedLines = markedElements;

		while (true) {
			currentChilds = ChildsLineParams(currentLine);
			currentLine = null;
			while ((currentChildLine = currentChilds.poll()) != null) {
				processingResult = ProcessChildLine(currentChildLine);
				if (processingResult > 0) {
					remainingDots -= processingResult;
					if (remainingDots == 0)
						return currentChildLine.distanceToFinal;
					currentLine = currentChildLine;
					break;
				}

			}
			if (currentLine == null)
				break;
		}
		markedElements = markedLines;
		return 0;
	}

	private long Process(Queue<LineParams> lines)
			throws PathNotFoundInMazeException {
		LineParams currentParrentLine;
		LineParams currentChildLine;
		Queue<LineParams> currentChilds;
		int processingResult = 0;
		while ((currentParrentLine = lines.poll()) != null) {
			currentChilds = ChildsLineParams(currentParrentLine);
			while ((currentChildLine = currentChilds.poll()) != null) {
				processingResult = ProcessChildLine(currentChildLine);
				if (processingResult < 0)
					continue;
				markedElements++;
				if (processingResult == 0) {
					lines.add(currentChildLine);
				} else {
					long distanceFromRobotToFinal = ProcessBeginLines(
							currentChildLine, processingResult);
					if (distanceFromRobotToFinal > 0)
						return distanceFromRobotToFinal;
					MatricMapper.MapCharObject(map, begining, K, '*');
				}
			}
		}

		throw new PathNotFoundInMazeException(
				"Unfortunatelly there is no a path in your maze.");
	}

	@Override
	public SolvingResult Solve(final MazeDTO maze)
			throws PathNotFoundInMazeException {
		K = maze.k;
		CompleteMapping();

		map = maze.mazeMap;
		W = maze.width;
		H = maze.height;
		begining = maze.robot;

		final SolvingResult result = new SolvingResult();
		result.distance = Process(EndLines(maze.end));
		result.markedElements = markedElements;

		return result;
	}
}
