package UtilityClasses;

import Exceptions.*;

/**
 * Utility class, containing most common value checks.
 * 
 * @author XRC_7331
 */
public final class StandartChecks {
	
	public static void CheckZeroOrPositive(int value) throws NotZeroOrPositiveValueException
	{
		if(value < 0)
		{
			throw new NotZeroOrPositiveValueException("\nExpected positive or zero value!\nYour: " + value);
		}
	}
	
	public static void CheckPositive(int value) throws NotPositiveValueException
	{
		if(value < 1)
		{
			throw new NotPositiveValueException("\nExpected positive value!\nYour: " + value);
		}
	}
	
	public static void CheckIX(int ix, int boundary) throws NotZeroOrPositiveValueException, InvalidIXException
	{
		CheckZeroOrPositive(ix);
		if(ix >= boundary)
		{
			throw new InvalidIXException("\nNot correct IX!\nBoundaru: " + boundary + "\nYour: " + ix);
		}
	}
}
