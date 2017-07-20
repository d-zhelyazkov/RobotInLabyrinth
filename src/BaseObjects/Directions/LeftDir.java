package BaseObjects.Directions;

import BaseObjects.Point;

public class LeftDir extends AbstractDirection {

	public LeftDir() {
		super(1, '<');
	}

	@Override
	public Point TransformPoint(final Point point, int steps) {
		return new Point(point.x - steps, point.y);
	}

	@Override
	public AbstractDirection[] DirectionTree() {
		final AbstractDirection[] tree = new AbstractDirection[3];
		tree[0] = this;
		tree[1] = new DownDir();
		tree[2] = new UpDir();
		return tree;
	}

}