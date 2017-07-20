package Main;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Vector;

public class StatisticRegistrator {
	private Vector<StatisticEntry> entries;

	public StatisticRegistrator() {
		entries = new Vector<StatisticEntry>();
	}

	public void Register(final String entryName, final String entryValue) {
		entries.add(new StatisticEntry(entryName, entryValue));
	}

	public void OutputToFile(final String fileName)
			throws FileNotFoundException {
		PrintWriter outFile = new PrintWriter(fileName);

		for (StatisticEntry entry : entries) {
			outFile.println(entry);
		}

		outFile.close();
	}
}
