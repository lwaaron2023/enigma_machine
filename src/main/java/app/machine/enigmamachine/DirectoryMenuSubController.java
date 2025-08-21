package app.machine.enigmamachine;

import IO.DirectoryNavigator;
import IO.FileNavigator;
import IO.MISCException;
import IO.Navigator;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.File;
import java.util.List;

public class DirectoryMenuSubController {

    /*
     * Identifiers:
     *
     * B: Button
     * TF: TextField
     * T: Text
     * PB: Progress bar
     * L: Label
     * CB: Check box
     *
     * Menu:
     *
     * M:Main
     * D:DirectorySelect&FileSelect
     *
     * Maps of current Panes
     *
     * File/Directory Menu:
     *_____________________________
     *|                            |
     *| TFD1                    BD5|
     *| TD1                        |
     *|                            |
     *| BD1     BD2      BD3    BD4|
     *|____________________________|
     *
     */


    /**Represents the home file of the computer*/
    private final File home = new File(System.getenv().get("HOMEPATH"));
    /**Represents maximum number of directories that can be displayed in the window*/
    private final int MAXDIRECTORIESPERPAGE = 27;
    /**Represents whether the function onUseHomeDirectory has been called*/
    private Navigator navigator;
    private List<Text> texts;
    private List<TextField> textFields;

    public DirectoryMenuSubController(List<Text> texts, List<TextField> textFields, boolean dMenu) {
        this.texts = texts;
        this.textFields = textFields;
        if(dMenu) {
            navigator = new DirectoryNavigator(MAXDIRECTORIESPERPAGE);
            this.textFields.get(1).setVisible(false);
            this.texts.get(1).setVisible(false);
        }
        else{
            navigator = new FileNavigator(MAXDIRECTORIESPERPAGE);
            this.textFields.get(1).setVisible(true);
            this.texts.get(1).setVisible(true);
        }

    }

    public void refreshValues(){
        texts.get(0).setText(navigator.formattedOutput());
        textFields.get(0).setText(navigator.getCurrentDirectory());
        if(navigator instanceof FileNavigator){
            String temp = navigator.getSelection();
            if(temp.length() > 0){
                if(temp.charAt(0) == 'f'){
                    textFields.get(1).setText(temp.substring(1));
                }
                else if(temp.charAt(0) == 'd'){
                    textFields.get(1).setText("");
                }
            }

        }
    }



    public void onButtonClick(String buttonId) throws MISCException {
        switch(buttonId){

            case "BD1":
                navigator.goUp();
                this.refreshValues();
                break;

            case "BD2":
                navigator.goDown();
                this.refreshValues();
                break;

            case "BD3":
                navigator.goOut();
                this.refreshValues();
                break;

            case "BD4":
                navigator.goIn();
                this.refreshValues();
                break;

            case "BD5":
                throw new MISCException(navigator.getFinishedOutput());


            default:
        }
    }

    /**
     * @param textFieldId
     */

    public void onTextInput(String textFieldId) {

    }
}
