package BaseObjects.Directions;

import BaseObjects.Point;

public class UpDir extends AbstractDirection {

	public UpDir() {
		super(2, '^');
	}

	@Override
	public Point TransformPoint(final Point point, int steps) {
		return new Point(point.x, point.y - steps);
	}

	@Override
	public AbstractDirection[] DirectionTree() {
		final AbstractDirection[] tree = new AbstractDirection[3];
		tree[0] = this;
		tree[1] = new LeftDir();
		tree[2] = new RightDir();
		return tree;
	}
}
