package app.machine.enigmamachine;

import IO.MISCException;
import IO.NewCipherIO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;

import java.io.File;
import java.util.List;

public class MainController {
    @FXML
    Pane MainMenu;
    @FXML
    Pane DirectoryMenu;
    /*
     * FXML ID General Format:
     * Identifier Menu Number
     *
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
     *|         TM1             |
     *|         TM2             |
     *|_________________________|
     *
     * File/Directory Menu:
     *_____________________________
     *|                            |
     *| T    TFD1                  |
     *| TD2  TFD2                  |
     *| TD1                        |
     *|                            |
     *| BD1  BD2  BD3  BD4    BD5  |
     *|____________________________|
     *
     */
    @FXML
    TextField TFM1;
    @FXML
    TextField TFM2;
    @FXML
    TextField TFM3;
    @FXML
    TextField TFD1;
    @FXML
    TextField TFD2;
    @FXML
    Text TM1;
    @FXML
    Text TM2;
    @FXML
    Text TD1;
    @FXML
    Text TD2;
    @FXML
    Button BM1;
    @FXML
    Button BM2;
    @FXML
    Button BM3;
    @FXML
    Button BM4;
    @FXML
    Button BM5;
    @FXML
    Button BM6;
    @FXML
    Button BM7;
    @FXML
    Button BD1;
    @FXML
    Button BD2;
    @FXML
    Button BD3;
    @FXML
    Button BD4;
    @FXML
    Button BD5;
    @FXML
    ProgressBar PBM1;

    /**Indicates whether the user has visited the main menu before**/
    private boolean firstBM;
    /**Is responsible for most functionality in the main menu*/
    private MainMenuSubController mSubController;
    /**Is responsible for most functionality in the directory/file menu**/
    private DirectoryMenuSubController dSubController;

    public MainController(){
        firstBM = true;
    }

    /**
     * This function determines which controller should respond to a button being pressed
     *
     * Buttons handled by main controller are: BM3, BM5, BM6, BM7
     * Buttons handled by main menu sub controller are: All buttons whose id start with BM and are not mentioned above
     * Buttons handled by directory menu sub controller are: All buttons whose id start with BD
     *
     * @param actionEvent the event generated when most things are interacted with in the gui
     */
    public void onButtonClick(ActionEvent actionEvent) {
        //System.out.println(actionEvent.getSource());

        if(actionEvent.getSource() instanceof Button){

            TM1.setText("");
            TM2.setText("");

            String buttonId = ((Button) actionEvent.getSource()).getId();
            ((Button) actionEvent.getSource()).disarm();
            //System.out.println(buttonId);
            //Makes sure that buttons are called by the correct class, certain buttons BM3/BM5/BD3 have special behaviors

            //Initialize Values for mSubController
            if(firstBM){
                mSubController = new MainMenuSubController(List.of(TM2) ,List.of(TFM1, TFM2, TFM3));
                firstBM = false;
            }

            switch(buttonId){
                //Select File Button
                case "BM3":
                    //Initialize Values for dSubController
                    dSubController = new DirectoryMenuSubController(List.of(TD1, TD2), List.of(TFD1, TFD2), false);
                    dSubController.refreshValues();
                    MainMenu.setVisible(false);
                    DirectoryMenu.setVisible(true);
                    break;
                //Select Directory Button
                case "BM5":
                    //Initialize Values for dSubController
                    dSubController = new DirectoryMenuSubController(List.of(TD1, TD2), List.of(TFD1, TFD2), true);
                    dSubController.refreshValues();
                    MainMenu.setVisible(false);
                    DirectoryMenu.setVisible(true);
                    break;


                //Encrypt Button
                case "BM6":

                    TM1.setText("Initializing...\n"+"Checking Key: ");
                    try {

                        File input = new File(TFM2.getText());
                        File outputLocation = new File(TFM3.getText());
                        int key = Integer.parseInt(TFM1.getText());
                        NewCipherIO ciphIO = new NewCipherIO(key);
                        TM1.setText("Initializing...\n"+"Checking Key: VALID\n"+"Checking Input File:");
                        if(input.exists()){
                            TM1.setText("Initializing...\n"+"Checking Key: VALID\n"+"Checking Input File: VALID\n"+"Checking Output Location:");
                            if(outputLocation.exists()){
                                TM1.setText("Initializing...\n"+"Checking Key: VALID\n"+"Checking Input File: VALID\n"+"Checking Output Location: VALID\nEncrypting...");
                                ciphIO.encryptFile(input, outputLocation);
                                TM1.setText("Initializing...\n"+"Checking Key: VALID\n"+"Checking Input File: VALID\n"+"Checking Output Location: VALID\nEncrypting...\nSUCCESS");
                                //PBM1.setProgress(0);
                                //TM2.setText("Done");
                            }
                            else{
                                TM1.setText("Initializing...\n"+"Checking Key: VALID\n"+"Checking Input File: FILE VALID\n"+"Checking Output Location: INVALID");
                                TM2.setText("Path "+TFM3.getText()+" is invalid. Please enter a valid filepath.");
                            }
                        }
                        else{
                            TM1.setText("Initializing...\n"+"Checking Key: KEY VALID\n"+"Checking Input File: INVALID");
                            TM2.setText("Path "+TFM2.getText()+" is invalid. Please enter a valid filepath.");
                        }
                    } catch (NumberFormatException e){
                        TM1.setText("Initializing...\n"+"Checking Key: INVALID\n");
                        TM2.setText("Key "+TFM1.getText()+" is invalid. Keys can only be integers.");
                    } catch(IOException e){
                        TM2.setText(e.getMessage());
                    }

                    break;
                //Decrypt Button
                case "BM7":

                    TM1.setText("Initializing...\n"+"Checking Key: ");
                    try {
                        File input = new File(TFM2.getText());
                        File outputLocation = new File(TFM3.getText());
                        int key = Integer.parseInt(TFM1.getText());
                        NewCipherIO ciphIO = new NewCipherIO(key);
                        TM1.setText("Initializing...\n"+"Checking Key: VALID\n"+"Checking Input File:");
                        if(input.exists()){
                            TM1.setText("Initializing...\n"+"Checking Key: VALID\n"+"Checking Input File: VALID\n"+"Checking Output Location:");
                            if(outputLocation.exists()){
                                TM1.setText("Initializing...\n"+"Checking Key: VALID\n"+"Checking Input File: VALID\n"+"Checking Output Location: LOCATION VALID\nDecrypting...");
                                ciphIO.decryptFile(input, outputLocation);
                                TM1.setText("Initializing...\n"+"Checking Key: VALID\n"+"Checking Input File: VALID\n"+"Checking Output Location: VALID\nDecrypting...\nSUCCESS");
                            }
                            else{
                                TM1.setText("Initializing...\n"+"Checking Key: VALID\n"+"Checking Input File: FILE VALID\n"+"Checking Output Location: INVALID");
                                TM2.setText("Path "+TFM3.getText()+" is invalid. Please enter a valid filepath.");
                            }
                        }
                        else{
                            TM1.setText("Initializing...\n"+"Checking Key: KEY VALID\n"+"Checking Input File: INVALID");
                            TM2.setText("Path "+TFM2.getText()+" is invalid. Please enter a valid filepath.");
                        }
                    } catch (NumberFormatException e){
                        TM1.setText("Initializing...\n"+"Checking Key: INVALID\n");
                        TM2.setText("Key "+TFM1.getText()+" is invalid. Keys can only be integers.");
                    } catch(IOException e){
                        TM2.setText(e.getMessage());
                    }

                    break;


                default:
                    if(buttonId.contains("BM")){
                        mSubController.onButtonClick(buttonId);
                    }
                    else if(buttonId.contains("BD")){
                        try {
                            dSubController.onButtonClick(buttonId);
                        } catch(MISCException e){
                            String temp = e.getMessage();
                            if(temp.charAt(0) == 'f') {
                                if(temp.length() > 1) {
                                    TFM2.setText(temp.substring(1));
                                    MainMenu.setVisible(true);
                                    DirectoryMenu.setVisible(false);
                                }
                                else{
                                    TFM2.setText("");
                                    MainMenu.setVisible(true);
                                    DirectoryMenu.setVisible(false);
                                }
                            }
                            else if(temp.charAt(0) == 'd') {
                                if(temp.length() > 1) {
                                    TFM3.setText(temp.substring(1));
                                    MainMenu.setVisible(true);
                                    DirectoryMenu.setVisible(false);
                                }
                                else{
                                    TFM3.setText("");
                                    MainMenu.setVisible(true);
                                    DirectoryMenu.setVisible(false);
                                }
                            }
                        }
                    }
            }

            ((Button) actionEvent.getSource()).arm();
            actionEvent.consume();
        }
        //subController.onButtonClick();
    }


}
