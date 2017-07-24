package Maze;
import java.util.NoSuchElementException;
import java.util.Scanner;

import BaseObjects.MazeDTO;
import BaseObjects.Point;
import Exceptions.InvalidMazeParameterException;
import Exceptions.InvalidMazeMapException;
import Exceptions.NotCorrectMapLineColsException;
import Exceptions.NotCorrectMapRowsException;
import Exceptions.NotValidMazeCharacterException;
import Interface.IMazeMapper;



/**
 * Maps a maze by a givven scanner.
 * 
 * @author XRC_7331
 */
public class ScannerMazeMapper implements IMazeMapper {

	private Scanner scanner;
	
	/**
	 * Construct a mapper based on a scanner.
	 * 
	 * @param scanner
	 */
	public ScannerMazeMapper(Scanner scanner)
	{
		this.scanner = scanner;
	}
	
	/* (non-Javadoc)
	 * @see MazeMapper#MapMaze()
	 */
	@Override
	public MazeDTO MapMaze() throws InvalidMazeParameterException, InvalidMazeMapException 
	{
		MazeDTO maze = new MazeDTO();
		
		maze.width = scanner.nextInt();
		maze.height = scanner.nextInt();
		maze.k = scanner.nextInt();
		maze.robot = new Point(scanner);
		maze.end = new Point(scanner);
		
		maze.CheckParameters();
		
		scanner.nextLine();
		GetMap(maze);
		
		
		return maze;
	}

	/* (non-Javadoc)
	 * @see MazeMapper#GetMap(int, int)
	 */
	@Override
	public void GetMap(MazeDTO maze) throws InvalidMazeMapException {
		maze.mazeMap = new char[maze.height][];
		
		for (int i = 0; i < maze.height; i ++)
		{
			try
			{
				maze.mazeMap[i] = scanner.nextLine().toCharArray();
			}
			catch (NoSuchElementException e)
			{
				throw new InvalidMazeMapException(e);
			}
		}
		
		try 
		{
			maze.CheckMap();
		}
		catch 
		(
			NotCorrectMapRowsException 
			| NotCorrectMapLineColsException 
			| NotValidMazeCharacterException e
		)
		{
			throw new InvalidMazeMapException(e);
		}
	}

	
	
	

}
