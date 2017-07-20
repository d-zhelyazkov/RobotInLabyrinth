
package UtilityClasses;

import java.io.File;

/**
 * Class with files' utilities.
 * @author XRC_7331
 *
 */
public class FileUtils {

    /*
     * Get the extension of a file.
     */
    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }
    
    /**
     * Removes the extension of the file full path.
     * @param The file
     * @return The cut full file name.
     */
    public static String getAbsoluteNameWithoutExtension(File f) {
        
        String s = f.getAbsolutePath();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            s = s.substring(0,i);
        }
        return s;
    }

}