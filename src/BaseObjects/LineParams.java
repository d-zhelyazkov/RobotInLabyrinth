package BaseObjects;

import BaseObjects.Directions.AbstractDirection;

/**
 * @author XRC_7331 Class representing beginning of a line and its direction.
 */
public class LineParams {
	public Point p;
	public AbstractDirection dir;
	public int distanceToFinal;

	public LineParams(final Point p, final AbstractDirection dir,
			int distanceToFinal) {
		this.p = new Point(p);
		this.dir = dir;
		this.distanceToFinal = distanceToFinal;
	}

	public LineParams(int x, int y, final AbstractDirection dir,
			int distanceToFinal) {
		this.p = new Point(x, y);
		this.dir = dir;
		this.distanceToFinal = distanceToFinal;
	}

}
