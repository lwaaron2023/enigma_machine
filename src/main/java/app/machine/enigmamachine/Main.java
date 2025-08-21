package app.machine.enigmamachine;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Parent rootMain = FXMLLoader.load(getClass().getResource("Main-view.fxml"));
        Scene sceneMain = new Scene(rootMain, 750, 750);
        stage.setTitle("E.Nigma Machine");
        stage.setScene(sceneMain);
        stage.show();

    }

    public static void main(String[] args) {
        launch();

    }


}