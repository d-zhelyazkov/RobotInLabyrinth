package BaseObjects.Directions;

import BaseObjects.Point;

public class DownDir extends AbstractDirection {

	public DownDir() {
		super(0, 'v');
	}

	@Override
	public Point TransformPoint(final Point point, int steps) {
		return new Point(point.x, point.y + steps);
	}

	@Override
	public AbstractDirection[] DirectionTree() {
		final AbstractDirection[] tree = new AbstractDirection[3];
		tree[0] = this;
		tree[1] = new RightDir();
		tree[2] = new LeftDir();
		return tree;
	}

}