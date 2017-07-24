package Main;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import BaseObjects.MazeDTO;
import Maze.MazeMonitor;
import Maze.MoveCommandHandler;
import Maze.RobotMovementSimulator;
import Maze.StandardMazeCommandExecutor;

/*
 * Arguments required: 1.maze(MazeDTO) 2.commandHandler(MoveCommandHandler)
 */
public class SimulateRobotMovement extends AbstractTimeMeasuredFunction {

	private static String WINDOW_TITLE = "Just a Maze";

	protected SimulateRobotMovement(final Object[] functionsParams,
			final StatisticRegistrator registrator) {
		super(functionsParams, registrator);
	}

	@Override
	protected Object Execute(final Object[] functionsParams) {
		final MazeDTO maze = (MazeDTO) functionsParams[0];
		final MoveCommandHandler commandHandler = (MoveCommandHandler) functionsParams[1];

		final MazeMonitor mazeMonitor = new MazeMonitor(maze);
		InitMainWindow(maze, mazeMonitor);
		RobotMovementSimulator sim = new RobotMovementSimulator(maze,
				new StandardMazeCommandExecutor(), commandHandler, mazeMonitor);
		sim.Start();

		return null;
	}

	@Override
	protected String GetTaskDescription() {

		return "Simulating";
	}

	private static void InitMainWindow(final MazeDTO maze,
			final MazeMonitor mazeMonitor) {
		JFrame window = new JFrame();

		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setSize(maze.width + 10, maze.height + 30);
		window.setLocation(10, 10);
		window.getContentPane().add(mazeMonitor);
		window.setResizable(false);
		window.setTitle(WINDOW_TITLE);
		window.setVisible(true);
	}

}
