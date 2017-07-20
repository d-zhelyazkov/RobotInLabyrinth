package UtilityClasses;

import BaseObjects.Point;

public final class MatricMapper {
	/**
	 * Remaps an object to a square.
	 * 
	 * @param map The map, containing the object.
	 * @param objectBeginning The object start point.
	 * @param size Square's a.
	 * @param symbol The object' used for mapping the object.
	 */
	public static void MapCharObject(char[][] map, Point objectBeginning, int size, char symbol)
	{
		int lastX = objectBeginning.x + size;
		int lastY = objectBeginning.y + size;
		
		for(int i = objectBeginning.y; i < lastY; i ++)
		{
			for(int j = objectBeginning.x; j < lastX; j ++)
			{
				map[i][j] = symbol;
			}
		}
	}
}
