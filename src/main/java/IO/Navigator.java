package IO;

import java.io.File;
import java.util.ArrayList;

public interface Navigator {

    /**
     * Returns the path of the directory the class is focussed on
     * @return String represents the path of the directory
     */
    public String getCurrentDirectory();
    /**
     * Returns the path of the highlighted value
     * @return String represents the path of the highlighted value
     */
    public String getSelection();
    /**
     * Returns the formatted output of the files and directories, starting at 0
     * @return String represents the first x amount of directory and files where x = MAXDIRECTORIESPERPAGE
     */
    public String formattedOutput();
    /**
     * Returns the formatted output of the files and directories, starting at start
     * @param start the first value at which the directories/files should be added to the output
     * @return String represents the first x amount of directory and files where x = MAXDIRECTORIESPERPAGE
     */
    public String formattedOutput(int start);
    /**
     * Goes up a value
     */
    public String goUp();
    /**
     * Goes down a value
     */
    public String goDown();
    /**
     * Goes into the directory with the <> surrounding it
     */
    public String goIn();
    /**
     * Returns to previous directory
     */
    public String goOut();

    public String getFinishedOutput();

}
