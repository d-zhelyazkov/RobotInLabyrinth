package ImageFileChoser;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import Exceptions.FileNoSelectedException;

/**
 * @author XRC_7331 Window for selecting pictures.
 */
public class ImageFileChooser {
	private JFileChooser fc;
	private File currentFile;

	/**
	 * Constructs the file chooser using the following settings: 1. Files only.
	 * 2. Images filter only. 3. Current directory selected. 4. Added picture
	 * preview, scaled to 600x600. 5. Not resizeable.
	 */
	public ImageFileChooser() {
		// Create a file chooser
		fc = new JFileChooser();
		File workingDirectory = new File(System.getProperty("user.dir"));
		fc.setCurrentDirectory(workingDirectory);
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.removeChoosableFileFilter(fc.getAcceptAllFileFilter());
		fc.addChoosableFileFilter(new ImageFilter());
		fc.setDialogTitle("Select an image");
		// Add the preview pane.
		fc.setAccessory(new ImagePreview(fc, 600));

		// GetNewFile();
	}

	/**
	 * Opens a window for selecting a file.
	 * 
	 * @return The selected file.
	 * @throws FileNoSelectedException.
	 */
	public File GetNewFile() throws FileNoSelectedException {
		JFrame chooserFrame = new JFrame();
		chooserFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int userChoice = fc.showOpenDialog(chooserFrame);
		if (userChoice != JFileChooser.APPROVE_OPTION) {
			throw new FileNoSelectedException();
		}

		currentFile = fc.getSelectedFile();
		return currentFile;

	}

	/**
	 * @return The last selected file.
	 * @throws FileNoSelectedException
	 *             If the user refuse to select an image.
	 */
	public File GetCurrentFile() throws FileNoSelectedException {
		if (currentFile == null) {
			GetNewFile();
		}

		return currentFile;
	}
}
