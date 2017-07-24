package BaseObjects;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import Exceptions.InvalidMazeConstantException;
import Exceptions.InvalidMazeParameterException;
import Exceptions.NotCorrectMapLineColsException;
import Exceptions.NotCorrectMapRowsException;
import Exceptions.NotPositiveValueException;
import Exceptions.NotValidMazeCharacterException;
import Main.StatisticRegistrator;
import UtilityClasses.StandartChecks;

/**
 * Holder, representing basic Maze parameter.
 * 
 * @author XRC_7331
 *
 */
public final class MazeDTO {
	public int width;
	public int height;

	/**
	 * Constant representing the size of the beginning and end of the Maze.
	 */
	public int k;

	/**
	 * Position of a robot.
	 */
	public Point robot;

	/**
	 * Position of the maze's end.
	 */
	public Point end;

	/**
	 * The map of the maze. Several symbols are allowed: ' ' - clear space; 'W"
	 * - obstacle; 'o' - end; '*' - beginning.
	 */
	public char[][] mazeMap;

	/**
	 * Makes sure that all parameters are valid.
	 * 
	 * @throws InvalidMazeParameterException
	 *             when there is not correct parameter.
	 */
	public void CheckParameters() throws InvalidMazeParameterException {
		try {
			StandartChecks.CheckPositive(width);
			StandartChecks.CheckPositive(height);
			CheckConstant();
			robot.Check();
			end.Check();
		} catch (NotPositiveValueException | InvalidMazeConstantException
				| Exceptions.NotZeroOrPositiveValueException e) {
			throw new InvalidMazeParameterException(e);
		}
	}

	/**
	 * Validates maze's map.
	 * 
	 * @throws NotCorrectMapRowsException
	 *             when map's rows are not equal to maze's height.
	 * @throws NotCorrectMapLineColsException
	 *             when a line's columns are not equal to maze's width.
	 * @throws NotValidMazeCharacterException
	 *             when a character is not in ['*','o',' ', 'W'].
	 */
	public void CheckMap() throws NotCorrectMapRowsException,
			NotCorrectMapLineColsException, NotValidMazeCharacterException {
		int mapRows = mazeMap.length;
		if (mapRows != height) {
			throw new NotCorrectMapRowsException("\nExpected: " + height
					+ "\nCurrent: " + mapRows);
		}

		char[] currentLine = null;
		int currentLineCols = 0;
		// char currentChar = ' ';
		for (int i = 0; i < mapRows; i++) {
			currentLine = mazeMap[i];
			currentLineCols = currentLine.length;
			if (currentLineCols != width) {
				throw new NotCorrectMapLineColsException("\nLine: " + i
						+ "\nExpected: " + width + "\nCurrent: "
						+ currentLineCols);
			}

			// TODO Needs to be adapted to char[] based direction mappings
			/*
			 * for(int j = 0; j < currentLineCols; j ++) { currentChar =
			 * currentLine[j]; switch(currentChar) { case '*': case 'o': case
			 * ' ': case 'W': case 'v': case '<': case '^': case '>': break;
			 * default: throw new NotValidMazeCharacterException("\n(" + j + ","
			 * + i + ")\nYour: " + currentChar); } }
			 */
		}

	}

	private void CheckConstant() throws NotPositiveValueException,
			InvalidMazeConstantException {
		StandartChecks.CheckPositive(k);
		if (k >= width / 2 || k >= height / 2) {
			throw new InvalidMazeConstantException(
					"\nA maze's constant is meant to be lower than its size parameters!\nWidth: "
							+ width + " Height:" + height + "\nYour constant: "
							+ k);
		}
	}

	public void OutputToFile(final String fileName) {

		CheckParameters();
		CheckMap();

		PrintWriter outFile;
		try {
			outFile = new PrintWriter(fileName);
			outFile.println(width);
			outFile.println(height);
			outFile.println(k);
			outFile.println(robot.x);
			outFile.println(robot.y);
			outFile.println(end.x);
			outFile.println(end.y);

			for (char[] row : mazeMap) {
				outFile.println(row);
			}

			outFile.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void RegisterInStatistics(final StatisticRegistrator register) {
		register.Register("\tWidth", Integer.toString(width));
		register.Register("\tHeight", Integer.toString(height));
		register.Register("\tK", Integer.toString(k));
	}
}
