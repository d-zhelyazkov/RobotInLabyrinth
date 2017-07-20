package Maze;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Vector;

import BaseObjects.MoveCommand;
import BaseObjects.Directions.AbstractDirection;

/**
 * Object, that contains robot movement commands.
 * 
 * @author XRC_7331
 *
 */
public class MoveCommandHandler {
	private Vector<MoveCommand> commands;
	private int iterator;

	public MoveCommandHandler() {
		commands = new Vector<MoveCommand>();
		ResetToBeginning();
	}

	public void InsertCommand(final AbstractDirection direction) {
		if (commands.isEmpty()) {
			commands.addElement(new MoveCommand(direction));
		} else {
			final MoveCommand lastCommand = commands.lastElement();
			if (lastCommand.direction.equals(direction)) {
				lastCommand.Increase();
			} else {
				commands.addElement(new MoveCommand(direction));
			}
		}

	}

	public void OutputToFile(final String fileName)
			throws FileNotFoundException {
		PrintWriter outFile = new PrintWriter(fileName);

		for (MoveCommand command : commands) {
			outFile.println(command);
		}

		outFile.close();
	}

	/**
	 * Moves command selector to the beginning of the handler.
	 */
	public void ResetToBeginning() {
		iterator = -1;
	}

	/**
	 * Gets the next command in the handler. If the selector is in the end of
	 * the handler, Null is returned and the selector is set to beginning.
	 * 
	 * @return The next command.
	 */
	public MoveCommand NextCommand() {
		MoveCommand nextCommand = null;
		iterator++;
		if (iterator < commands.size()) {
			nextCommand = commands.get(iterator);
		} else {
			ResetToBeginning();
		}

		return nextCommand;
	}
}
