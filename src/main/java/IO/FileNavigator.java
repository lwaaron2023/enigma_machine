package IO;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

public class FileNavigator implements Navigator{
    private final File home = new File(System.getenv().get("HOMEPATH"));

    private File currentDirectory;

    private int selectedDirectory;

    private LinkedList<File> allFiles;

    private int maxLength;
    public FileNavigator(int maxLength){
        this.currentDirectory = home;
        this.directoryChanged();
        this.maxLength = maxLength;
    }

    public String getCurrentDirectory(){
        return currentDirectory.getPath();
    }

    public String getSelection(){
        if(selectedDirectory != -1) {
            if(allFiles.get(selectedDirectory).isDirectory()) {
                return "d" + allFiles.get(selectedDirectory).getPath();
            }
            else{
                return "f" + allFiles.get(selectedDirectory).getPath();
            }
        }
        else{
            return "";
        }
    }
    public String formattedOutput(){
        if(selectedDirectory >= 0) {
            return this.formattedOutput(selectedDirectory);
        }
        else{
            return "";
        }
    }
    public String formattedOutput(int start){
        if(start >= 0) {
            int iteration = 0;
            String output = "";
            for (int i = start; i < allFiles.size() && iteration < maxLength; i++) {
                if (i == start) {
                    if(allFiles.get(i).isDirectory()) {
                        output += "<\t Directory: " + allFiles.get(i).getPath() + "\t>\n";
                    }
                    else{
                        output += "<\t File: " + allFiles.get(i).getPath() + "\t>\n";
                    }
                } else {
                    if(allFiles.get(i).isDirectory()) {
                        output += "Directory: "+allFiles.get(i).getPath() + "\n";
                    }
                    else{
                        output += "File: "+allFiles.get(i).getPath() + "\n";
                    }

                }
                iteration++;
            }
            return output;
        }
        else{
            return "";
        }
    }



    /**
     * After current directory is changed this function sets the updates the fields for selectedDirectory, subDirectory, and allFiles
     *
     * IMPORTANT if currentDirectory has no subdirectories, then selectedDirectory will be given the value of -1
     */
    private void directoryChanged(){

        allFiles = new LinkedList<>();
        File[] temp = currentDirectory.listFiles();

        for(File f: temp){
            if(!f.isHidden()){
                allFiles.add(f);
            }
        }

        if(allFiles.size() > 0){
            selectedDirectory = 0;
        }
        else{
            selectedDirectory = -1;
        }
    }

    /**
     * Changes selectedDirectory to either 0 or 1 less than the current value
     * @return String representing what is viewable in the text box
     */
    public String goUp(){
        if(selectedDirectory != -1){
            if(selectedDirectory > 0){
                selectedDirectory--;
            }
            return this.formattedOutput(selectedDirectory);
        }
        else {
            return "";
        }
    }
    /**
     * Changes selectedDirectory to either  or 1 more than the current value
     * @return String representing what is viewable in the text box
     */
    public String goDown(){
        if(selectedDirectory != -1){
            if(selectedDirectory < allFiles.size()-1){
                selectedDirectory++;
            }
            return this.formattedOutput(selectedDirectory);
        }
        else {
            return "";
        }
    }
    /**
     * Changes the current directory the directory at location selectedDirectory in subDirectories
     * @return String representing what is viewable in the text box
     */
    public String goIn(){
        if(selectedDirectory != -1){
            File temp = allFiles.get(selectedDirectory);
            if(temp.isDirectory()) {
                currentDirectory = allFiles.get(selectedDirectory);
                this.directoryChanged();
            }
            return this.formattedOutput(0);
        }
        else{
            return "";
        }
    }

    public String goOut(){

        if(!currentDirectory.getPath().equals(home.getPath())){
            currentDirectory = currentDirectory.getParentFile();
            this.directoryChanged();
            return this.formattedOutput(0);
        }

        else{
            return "";
        }
    }

    public String getFinishedOutput() {
        if(selectedDirectory >= 0) {
            File temp = allFiles.get(selectedDirectory);
            if(!temp.isDirectory()) {
                return "f" + allFiles.get(selectedDirectory).getPath();
            }
            else {
                return "f";
            }
        }
        else{
            return "f";
        }
    }
}
