package Main;

import java.io.File;
import java.util.Properties;

import BaseObjects.MazeDTO;
import Exceptions.PathNotFoundInMazeException;
import Maze.SolvingResult;
import UtilityClasses.FileUtils;
import UtilityClasses.StaticStrings;

/*
 * Arguments required: 1.mazeImage(File)
 */
public class MainProcess extends AbstractTimeMeasuredFunction {
	private static String SOLVED_SUFIX = "_solved.txt";
	private static String PARSED_SUFIX = "_parsed.txt";
	private static String COMMANDS_SUFIX = "_commands.txt";

	private String maze_url;
	private Properties configuration;
	private StatisticRegistrator registrator;

	protected MainProcess(final Object[] functionsParams,
			final StatisticRegistrator registrator,
			final Properties configuration) {
		super(functionsParams, registrator);
		this.registrator = registrator;
		this.configuration = configuration;
	}

	@Override
	protected Object Execute(Object[] functionsParams) {

		final File mazeImage = (File) functionsParams[0];

		maze_url = FileUtils.getAbsoluteNameWithoutExtension(mazeImage);

		final Object maze = ConstructMaze(mazeImage);

		SolveMaze(maze);

		final Object commandHandler = ExtractCommands(maze);

		if (configuration.getProperty(StaticStrings.MONITOR_SIMULATION_FLAG)
				.equals("1")) {
			SimulateRobotMovement(maze, commandHandler);
		}
		return null;
	}

	@Override
	protected String GetTaskDescription() {
		return "Whole process";
	}

	private Object ConstructMaze(final File mazeImage) {

		final Boolean isPicture = configuration.getProperty(
				StaticStrings.PICTURE_FILE_FLAG).equals("1");
		final String parsedMazeFileName = (isPicture) ? maze_url + PARSED_SUFIX
				: null;
		final Object[] arguments = { mazeImage, isPicture, parsedMazeFileName };
		final Object maze = new ConstructMaze(arguments, registrator).Execute();

		((MazeDTO) maze).RegisterInStatistics(this.GetRegistrator());
		return maze;
	}

	private void SimulateRobotMovement(final Object maze,
			final Object commandHandler) {

		Object[] arguments = { maze, commandHandler };
		new SimulateRobotMovement(arguments, registrator).Execute();

	}

	private Object ExtractCommands(final Object maze) {

		final String commandsFileName = maze_url + COMMANDS_SUFIX;
		final Object[] arguments = { maze, commandsFileName };

		return new ExtractCommands(arguments, registrator).Execute();
	}

	private void SolveMaze(final Object maze)
			throws PathNotFoundInMazeException {

		final String solvedMazeFileName = maze_url + SOLVED_SUFIX;
		final Object[] arguments = { maze, solvedMazeFileName };

		final SolvingResult result = (SolvingResult) new SolveMaze(arguments,
				registrator).Execute();
		result.RegisterInStatistics(registrator);

	}

}
