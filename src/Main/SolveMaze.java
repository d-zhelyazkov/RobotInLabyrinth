package Main;

import BaseObjects.MazeDTO;
import Interface.IMazeSolver;
import Maze.GasolisationMazeSolver;
import Maze.SolvingResult;

/*
 * Arguments required: 1.maze(MazeDTO) 2.resultFileName(String)
 */
public class SolveMaze extends AbstractTimeMeasuredFunction {

	protected SolveMaze(final Object[] functionsParams,
			final StatisticRegistrator registrator) {
		super(functionsParams, registrator);
	}

	@Override
	protected Object Execute(Object[] functionsParams) {

		final MazeDTO maze = (MazeDTO) functionsParams[0];
		final String resultFileName = (String) functionsParams[1];

		IMazeSolver solver = new GasolisationMazeSolver();
		final SolvingResult result = solver.Solve(maze);

		maze.OutputToFile(resultFileName);

		return result;
	}

	@Override
	protected String GetTaskDescription() {
		return "Solving (core algorithm)";
	}

}
