package Main;

public class StatisticEntry {
	private String name;
	private String value;

	public StatisticEntry(final String name, final String value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public String toString() {
		return name + ": " + value;
	}
}
