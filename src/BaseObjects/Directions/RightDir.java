package BaseObjects.Directions;

import BaseObjects.Point;

public class RightDir extends AbstractDirection {

	public RightDir() {
		super(3, '>');
	}

	@Override
	public Point TransformPoint(final Point point, int steps) {
		return new Point(point.x + steps, point.y);
	}

	@Override
	public AbstractDirection[] DirectionTree() {
		final AbstractDirection[] tree = new AbstractDirection[3];
		tree[0] = this;
		tree[1] = new UpDir();
		tree[2] = new DownDir();
		return tree;
	}

}
