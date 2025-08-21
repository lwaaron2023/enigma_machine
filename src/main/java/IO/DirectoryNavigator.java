package IO;

import java.io.File;
import java.util.ArrayList;

public class DirectoryNavigator implements Navigator{

    private final File home = new File(System.getenv().get("HOMEPATH"));

    private File currentDirectory;

    private int selectedDirectory;

    private ArrayList<File> subDirectories;

    private int maxLength;
    public DirectoryNavigator(int maxLength){
        this.currentDirectory = home;
        subDirectories = new ArrayList<>();
        this.directoryChanged();
        this.maxLength = maxLength;
    }

    public String getCurrentDirectory(){
        return currentDirectory.getPath();
    }

    public String getSelection(){
        if(selectedDirectory != -1) {
            return subDirectories.get(selectedDirectory).getPath();
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
            for (int i = start; i < subDirectories.size() && iteration < maxLength; i++) {
                if (i == start) {
                    output += "<\t Directory: " + subDirectories.get(i).getPath() + "\t>\n";
                } else {
                    output += "Directory: "+subDirectories.get(i).getPath() + "\n";
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

        subDirectories = new ArrayList<>();
        for(File f: currentDirectory.listFiles()){
            //System.out.print(f+"\t");
            if(f.isDirectory()){
                subDirectories.add(f);
            }
        }
        //System.out.println();

        if(subDirectories.size() > 0){
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
            if(selectedDirectory < subDirectories.size()-1){
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
            //System.out.println(subDirectories);
            currentDirectory = subDirectories.get(selectedDirectory);
            this.directoryChanged();
            //System.out.println(subDirectories);
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

    @Override
    public String getFinishedOutput() {
        return "d"+currentDirectory.getPath();
    }
}
