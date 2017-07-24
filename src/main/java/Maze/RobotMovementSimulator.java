package Maze;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import BaseObjects.MazeDTO;
import BaseObjects.MoveCommand;
import Interface.IMazeCommandExecutor;

public class RobotMovementSimulator {
	private MazeDTO maze;
	private MoveCommandHandler commands;
	private MazeMonitor monitor;
	private IMazeCommandExecutor executor;
	private Timer timer;

	public RobotMovementSimulator(final MazeDTO maze,
			final IMazeCommandExecutor executor,
			final MoveCommandHandler commands, final MazeMonitor monitor) {
		this.commands = commands;
		this.monitor = monitor;
		this.maze = maze;
		this.executor = executor;
		timer = new Timer(1000, new Movement());
	}

	public void Start() {
		timer.start();
	}

	class Movement implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {

			MoveCommand command = commands.NextCommand();
			if (command != null) {
				executor.ExecuteNext(maze, command);
				monitor.repaint();
			} else {
				timer.stop();
			}
		}
	}

}
