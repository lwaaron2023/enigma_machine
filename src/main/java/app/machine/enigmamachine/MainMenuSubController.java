package app.machine.enigmamachine;

import IO.DirectoryNavigator;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.File;
import java.util.List;

public class MainMenuSubController{

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
     * MainMenu:
     *___________________________
     *|                         |
     *|                         |
     *| L   TFM1    BM1         |
     *| L   TFM2    BM2    BM3  |
     *| L   TFM3    BM4    BM5  |
     *|                         |
     *|         BM6             |
     *|         PB              |
     *|         TM1             |
     *|_________________________|
     */
    private List<Text> texts;
    private List<TextField> textFields;

    private final int MAXKEYRANDOMVALUE = 1000;



    public MainMenuSubController(List<Text> texts, List<TextField> textFields) {
        this.texts = texts;
        this.textFields = textFields;

    }

    /**
     * @param buttonId
     */

    public void onButtonClick(String buttonId) {
        switch (buttonId){
            //Generate key button
            case "BM1":
                int key = (int) (MAXKEYRANDOMVALUE * Math.random()) + 1;
                textFields.get(0).setText(key+"");
                break;
            //Reset File Button
            case "BM2":
                textFields.get(1).setText("");
                break;

            //Reset Directory Button
            case "BM4":
                textFields.get(2).setText("");
                break;


        }
    }




}
