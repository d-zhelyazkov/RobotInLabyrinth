package Maze;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import BaseObjects.MazeDTO;

public class MazeMonitor extends JPanel {
	
	private static final long serialVersionUID = 3214531628566213674L;
	private MazeDTO maze;
	
	public MazeMonitor(final MazeDTO maze){
		this.maze = maze;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.WHITE);
	    g.fillRect (0, 0, maze.width, maze.height);
	    
	    DrawWalls(g);
	    
	    g.setColor(Color.GREEN);
	    g.fillRect(maze.end.x, maze.end.y, maze.k, maze.k);
	    
	    g.setColor(Color.RED);
	    g.fillRect(maze.robot.x, maze.robot.y, maze.k, maze.k);
	    
	    
	}
	
	
	private void DrawWalls(Graphics g)
	{
		g.setColor(Color.BLACK);
		for(int i = 0; i < maze.width; i ++)
		{
			for(int j = 0; j < maze.height; j ++)
			{
				if(maze.mazeMap[j][i] == 'W')
				{
					g.drawLine(i,j,i,j);
				}
			}
		}
	}
}
