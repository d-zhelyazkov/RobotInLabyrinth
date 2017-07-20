package Maze;

import Main.StatisticRegistrator;

public class SolvingResult {
	public long distance;
	public long markedElements;

	public void RegisterInStatistics(final StatisticRegistrator register) {
		register.Register("\tMarked elements", Long.toString(markedElements));
		register.Register("\tDistance", Long.toString(distance));
	}
}
