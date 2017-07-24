package Main;

import Exceptions.FileNoSelectedException;
import Exceptions.InvalidMazeException;
import Exceptions.PathNotFoundInMazeException;
import ImageFileChoser.ImageFileChooser;
import UtilityClasses.FileUtils;
import UtilityClasses.StaticStrings;

import javax.swing.JOptionPane;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class RobotInLabyrinthByPicture {

	private static Properties CONFIGURATION;

	private static final String STATISTIC_SUFFIX = "_statistics.txt";

	private static StatisticRegistrator REGISTER;

	public static void main(String[] args) throws IOException {

		RobotInLabyrinthByPicture mainApp = new RobotInLabyrinthByPicture();
		try {
			final File mazeImage = mainApp.initApplication();
			mainApp.process(mazeImage);
			REGISTER.OutputToFile(FileUtils
					.getAbsoluteNameWithoutExtension(mazeImage)
					+ STATISTIC_SUFFIX);

		} catch (InvalidMazeException e) {
			mainApp.outputMessage("Unfortunately there is a problem with your maze\n"
					+ "Error message: " + e.getMessage());
			e.printStackTrace();
		} catch (FileNoSelectedException ignored) {
		} catch (PathNotFoundInMazeException e) {
			mainApp.outputMessage(
					"Unfortunately your maze cannot be solved.\nThere is no path from the robot to the final.");
		} catch (Exception e) {
			mainApp.outputMessage(e.getMessage());
			e.printStackTrace();
		}

	}

	private void process(final File mazeImage) {
		Object[] arguments = { mazeImage };
		new MainProcess(arguments, REGISTER, CONFIGURATION).Execute();
	}

	private File initApplication() throws IOException,
			FileNoSelectedException {

		InputStream configStream = null;

		try {
			CONFIGURATION = new Properties();
			CONFIGURATION.setProperty(StaticStrings.SWING_SUPPORTED, "0");

			configStream = this.getClass().getClassLoader().getResourceAsStream(StaticStrings.CONFIG_FILE_NAME);
			CONFIGURATION.load(configStream);

			final File mazeImage;

			if (CONFIGURATION.getProperty(StaticStrings.IMAGE_CHOOSER_FLAG).equals(
					"1")) {
				CONFIGURATION.setProperty(StaticStrings.PICTURE_FILE_FLAG, "1");
				CONFIGURATION.setProperty(StaticStrings.SWING_SUPPORTED, "1");
				ImageFileChooser imageChooser = new ImageFileChooser();
				mazeImage = imageChooser.GetNewFile();
			} else {
				mazeImage = new File(CONFIGURATION.getProperty(StaticStrings.FILE_NAME));
			}

			if (CONFIGURATION.getProperty(StaticStrings.MONITOR_SIMULATION_FLAG)
					.equals("1"))
				CONFIGURATION.setProperty(StaticStrings.SWING_SUPPORTED, "1");

			System.out.println(CONFIGURATION.toString());

			REGISTER = new StatisticRegistrator();

			return mazeImage;

		} finally {
			if (configStream != null) {
				configStream.close();
			}
		}
	}

	private void outputMessage(final String message) {
		System.out.println(message);

		if (CONFIGURATION.getProperty(StaticStrings.SWING_SUPPORTED)
				.equals("1"))
			JOptionPane.showMessageDialog(null, message, "Warning",
					JOptionPane.WARNING_MESSAGE);
	}

}
