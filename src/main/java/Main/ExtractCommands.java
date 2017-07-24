package Main;

import java.io.FileNotFoundException;

import BaseObjects.MazeDTO;
import Interface.ICommandExtractor;
import Maze.MoveCommandHandler;
import Maze.StandartCommandExtractor;

/*
 * Arguments required: 1.maze(MazeDTO) 2.resultFileName(String)
 */
public class ExtractCommands extends AbstractTimeMeasuredFunction {

	protected ExtractCommands(final Object[] functionsParams,
			final StatisticRegistrator registrator) {
		super(functionsParams, registrator);
	}

	@Override
	protected Object Execute(final Object[] functionsParams) {

		final MazeDTO maze = (MazeDTO) functionsParams[0];
		final String resultFileName = (String) functionsParams[1];
		ICommandExtractor extractor = new StandartCommandExtractor();

		final MoveCommandHandler commandHandler = extractor.Extract(maze);

		try {
			commandHandler.OutputToFile(resultFileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return commandHandler;
	}

	@Override
	protected String GetTaskDescription() {

		return "Command extraction";
	}
}
