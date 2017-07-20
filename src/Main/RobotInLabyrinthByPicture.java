package Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JOptionPane;

import Exceptions.FileNoSelectedException;
import Exceptions.InvalidMazeException;
import Exceptions.PathNotFoundInMazeException;
import ImageFileChoser.ImageFileChooser;
import UtilityClasses.FileUtils;
import UtilityClasses.StaticStrings;

public class RobotInLabyrinthByPicture {

	private static Properties CONFIGURATION;

	private static String STATISTIC_SUFIX = "_statistics.txt";

	private static StatisticRegistrator REGISTER;

	public static void main(String[] args) throws IOException {

		try {
			final File mazeImage = InitApplication();
			Process(mazeImage);
			REGISTER.OutputToFile(FileUtils
					.getAbsoluteNameWithoutExtension(mazeImage)
					+ STATISTIC_SUFIX);

		} catch (InvalidMazeException e) {
			OutputMessage("Unfortunatelly there is a problem with your maze\n"
					+ "Error message: " + e.getMessage());
			e.printStackTrace();
		} catch (FileNoSelectedException e) {
		} catch (PathNotFoundInMazeException e) {
			OutputMessage("Unfortunatelly your maze cannot be solved.\nThere is no path from the robot to the final.");
		} catch (Exception e) {
			OutputMessage(e.getMessage());
			e.printStackTrace();
		}

	}

	private static void Process(final File mazeImage) {
		Object[] arguments = { mazeImage };
		new MainProcess(arguments, REGISTER, CONFIGURATION).Execute();
	}

	private static File InitApplication() throws IOException,
			FileNoSelectedException {

		CONFIGURATION = new Properties();
		CONFIGURATION.setProperty(StaticStrings.SWING_SUPPORTED, "0");
		CONFIGURATION.load(new FileInputStream(StaticStrings.CONFIG_FILE_NAME));

		final File mazeImage;

		if (CONFIGURATION.getProperty(StaticStrings.IMAGE_CHOOSER_FLAG).equals(
				"1")) {
			CONFIGURATION.setProperty(StaticStrings.PICTURE_FILE_FLAG, "1");
			CONFIGURATION.setProperty(StaticStrings.SWING_SUPPORTED, "1");
			ImageFileChooser imageChooser = new ImageFileChooser();
			mazeImage = imageChooser.GetNewFile();
		} else {
			mazeImage = new File(
					CONFIGURATION.getProperty(StaticStrings.FILE_NAME));
		}

		if (CONFIGURATION.getProperty(StaticStrings.MONITOR_SIMULATION_FLAG)
				.equals("1"))
			CONFIGURATION.setProperty(StaticStrings.SWING_SUPPORTED, "1");

		System.out.println(CONFIGURATION.toString());

		REGISTER = new StatisticRegistrator();

		return mazeImage;
	}

	private static void OutputMessage(final String message) {
		System.out.println(message);

		if (CONFIGURATION.getProperty(StaticStrings.SWING_SUPPORTED)
				.equals("1"))
			JOptionPane.showMessageDialog(null, message, "Warning",
					JOptionPane.WARNING_MESSAGE);
	}

}
