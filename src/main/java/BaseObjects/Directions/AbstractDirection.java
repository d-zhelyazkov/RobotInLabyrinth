package BaseObjects.Directions;

import BaseObjects.Point;

/**
 * @author XRC_7331 Basic implementation of an direction.
 */
public abstract class AbstractDirection {
	public int id;
	public char symbol;

	/**
	 * @param id
	 *            Identifier of the direction
	 * @param symbol
	 *            Symbol, representing the direction.
	 */
	protected AbstractDirection(int id, char symbol) {
		this.id = id;
		this.symbol = symbol;
	}

	@Override
	public final String toString() {
		return String.valueOf(symbol);
	}

	/**
	 * Compares two directions
	 * 
	 * @param dir
	 *            The compared to direction.
	 * @return True if the direction is the same as this one.
	 */
	public final boolean equals(final AbstractDirection dir) {
		return (id == dir.id);

	}

	/**
	 * Gets a point,which neighbours the given point in the current direction.
	 * 
	 * @param point
	 * @return
	 */
	public abstract Point TransformPoint(final Point point, int steps);

	public Point TransformPoint(final Point point) {
		return TransformPoint(point, 1);
	}

	/**
	 * Gets the possible directions, which are next to this one.
	 * 
	 * @return
	 */
	public abstract AbstractDirection[] DirectionTree();
}
