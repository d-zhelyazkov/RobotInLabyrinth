package BaseObjects;

import java.util.Scanner;

import UtilityClasses.StandartChecks;
import Exceptions.NotZeroOrPositiveValueException;

/**
 * Holder, containing basic 2D ix's.
 * 
 * @author XRC_7331
 */
public class Point {
	public int x;
	public int y;
	
	/**
	 * Constructs the point
	 * 		with common notValid values:
	 * 		(-1,-1).
	 */
	public Point()
	{
		x = -1;
		y = -1;
	}
	
	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Point(final Point p)
	{
		x = p.x;
		y = p.y;
	}
	
	public Point(Scanner scanner)
	{
		x = scanner.nextInt();
		y = scanner.nextInt();
	}
	
	/**
	 * Checks point's IXs.
	 * 
	 * @throws NotZeroOrPositiveValueException when one of them is negative.
	 */
	public void Check() throws NotZeroOrPositiveValueException
	{
		StandartChecks.CheckZeroOrPositive(x);
		StandartChecks.CheckZeroOrPositive(y);
	}
}
