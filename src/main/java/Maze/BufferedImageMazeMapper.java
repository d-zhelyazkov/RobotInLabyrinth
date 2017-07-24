package Maze;
import java.awt.image.BufferedImage;

import BaseObjects.MazeDTO;
import BaseObjects.Point;
import Exceptions.InvalidMazeParameterException;
import Exceptions.InvalidMazeMapException;
import Interface.IMazeMapper;
import UtilityClasses.MatricMapper;


/**
 * Maps a maze by given Image buffer.
 * 
 * @author XRC_7331
 */
public class BufferedImageMazeMapper implements IMazeMapper {
	
	private BufferedImage img;
	private Point robotMin;
	private Point robotMax;
	private Point endMin;
	private Point endMax;
	
	public BufferedImageMazeMapper(BufferedImage img)
	{
		this.img = img;
		robotMin = new Point();
		robotMax = new Point();
		endMin = new Point();
		endMax = new Point();
	}
	
	/* (non-Javadoc)
	 * @see MazeMapper#MapMaze()
	 */
	@Override
	public MazeDTO MapMaze() 
		throws InvalidMazeParameterException, InvalidMazeMapException 
	{
		MazeDTO maze = new MazeDTO();
		GetMap(maze);
		MazeParameters(maze);
		maze.CheckParameters();
		
		MatricMapper.MapCharObject(maze.mazeMap,maze.robot,maze.k,'*');
		MatricMapper.MapCharObject(maze.mazeMap,maze.end,maze.k,'o');
		
		
		return maze;
	}

	/* (non-Javadoc)
	 * @see MazeMapper#GetMap(MazeDTO)
	 */
	@Override
	public void GetMap(MazeDTO maze) throws InvalidMazeMapException 
	{
		int imgH = maze.height = img.getHeight();
		int imgW = maze.width = img.getWidth();
		char[][] map = new char[imgH][imgW];
		int[] rgb;
		
		for(int i = 0; i < imgH; i++)
		{
			for(int j = 0; j < imgW; j++)
			{
				rgb = PixelData( j, i);
				if(rgb[0] > 200 && rgb[1] < 100 && rgb[2] < 100)
				{
					map[i][j] = '*';
					ExpectTwoPairsOfIXs(robotMin,robotMax, j, i);
				}
				else if(rgb[0] < 100 && rgb[1] > 200 && rgb[2] < 100)
				{
					
					
					map[i][j] = 'o';
					ExpectTwoPairsOfIXs(endMin,endMax, j, i);
				}
				else if(rgb[0] >= 200 && rgb[1] >= 200 && rgb[2] >= 200)
				{
					map[i][j] = ' ';
				}
				else
				{
					map[i][j] = 'W';
				}
               
            }
        }
		
		maze.mazeMap = map;
		
	}
	
	
	
	/**
	 * Computes maze basic parameters.
	 * 
	 * @param maze
	 */
	private void MazeParameters(MazeDTO maze)
	{
		maze.robot = robotMin;
		maze.end = endMin;
		
		int currentK = 0;
		int maxK = 0;
		
		maxK = robotMax.x - robotMin.x + 1;
		
		currentK = robotMax.y - robotMin.y + 1;
		if(maxK < currentK)
		{
			maxK = currentK;
		}
		
		currentK = endMax.x - endMin.x + 1;
		if(maxK < currentK)
		{
			maxK = currentK;
		}
		
		currentK = endMax.y - endMin.y + 1;
		if(maxK < currentK)
		{
			maxK = currentK;
		}
		
		maze.k = maxK;
	}
	
	/**
	 * Compares current points' coordinates to given IXs. 
	 * 
	 * @param min
	 * @param max
	 * @param x
	 * @param y
	 */
	private void ExpectTwoPairsOfIXs(Point min, Point max, int x, int y)
	{
		if(min.x == -1)
		{
			max.x = min.x = x;
		}
		else
		{
			if(min.x > x)
			{
				min.x = x;
			}
			else if(max.x < x)
			{
				max.x = x;
			}
		}
		
		if(min.y == -1)
		{
			max.y = min.y = y;
		}
		else
		{
			if(min.y > y)
			{
				min.y = y;
			}
			else if(max.y < y)
			{
				max.y = y;
			}
		}
	}
	
	
	/**
	 * Stores a pixel in rgb int[3].
	 * 
	 * @param x
	 * @param y
	 * @return The rgb values of the pixel.
	 */
	private int[] PixelData(int x, int y) 
	{
		int argb = img.getRGB(x, y);
		int rgb[] = new int[] 
		{
			(argb >> 16) & 0xff, //red
			(argb >>  8) & 0xff, //green
			(argb      ) & 0xff  //blue
		};

		return rgb;
	}
}
