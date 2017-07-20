package Main;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import BaseObjects.MazeDTO;
import Exceptions.InvalidMazeException;
import Exceptions.InvalidMazeMapException;
import Exceptions.InvalidMazeParameterException;
import Exceptions.NotCorrectMapLineColsException;
import Exceptions.NotCorrectMapRowsException;
import Exceptions.NotValidMazeCharacterException;
import Interface.IMazeMapper;
import Maze.BufferedImageMazeMapper;
import Maze.ScannerMazeMapper;

/*
 * Arguments required: 1.mazeImage(File) 2.isPicture(boolean)
 * 3.resultFileName(String) - only if picture
 */
public class ConstructMaze extends AbstractTimeMeasuredFunction {

	protected ConstructMaze(final Object[] functionsParams,
			final StatisticRegistrator registrator) {
		super(functionsParams, registrator);
	}

	@Override
	protected Object Execute(Object[] functionsParams) {

		final File mazeImage = (File) functionsParams[0];
		final Boolean isPicutre = (Boolean) functionsParams[1];

		final MazeDTO maze;
		try {
			IMazeMapper mapper = null;

			if (isPicutre) {
				mapper = new BufferedImageMazeMapper(ImageIO.read(mazeImage));
			} else {
				Scanner scanner = new Scanner(mazeImage);
				mapper = new ScannerMazeMapper(scanner);
			}

			maze = mapper.MapMaze();

			if (isPicutre) {
				final String resultFileName = (String) functionsParams[2];
				maze.OutputToFile(resultFileName);
			}
		} catch (InvalidMazeParameterException | NotCorrectMapRowsException
				| NotCorrectMapLineColsException
				| NotValidMazeCharacterException | InvalidMazeMapException
				| IOException e) {
			throw new InvalidMazeException(e);
		}

		return maze;
	}

	@Override
	protected String GetTaskDescription() {
		return "Maze construction";
	}

}
