package Interface;
import BaseObjects.MazeDTO;
import Exceptions.InvalidMazeParameterException;
import Exceptions.InvalidMazeMapException;


/**
 * Interface, which creates a Maze object from derivated object.
 * 
 * @author XRC_7331
 */
public interface IMazeMapper {
	
	/**
	 * Creates Maze data transfer object and its Map.
	 * 
	 * @return the DTO.
	 * @throws InvalidMazeParameterException
	 * @throws InvalidMazeMapException 
	 */
	public MazeDTO MapMaze() throws InvalidMazeParameterException, InvalidMazeMapException;
	
	/**
	 * Translates the source into character map.
	 * Puts it in the maze;
	 * 
	 * @param maze
	 * @throws InvalidMazeMapException 
	 */
	public void GetMap(MazeDTO maze) throws InvalidMazeMapException;
}
