package BaseObjects;

import BaseObjects.Directions.AbstractDirection;

public class MoveCommand {
	public AbstractDirection direction;
	private int steps;

	public MoveCommand(final AbstractDirection direction) {
		this.direction = direction;
		steps = 1;
	}

	public void Increase() {
		steps++;
	}

	public Point TransformPoint(final Point point) {
		return direction.TransformPoint(point, steps);

	}

	@Override
	public String toString() {
		return direction.toString() + " " + steps;
	}
}
