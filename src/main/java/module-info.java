module app.machine.enigmamachine {
    requires javafx.controls;
    requires javafx.fxml;


    opens app.machine.enigmamachine to javafx.fxml;
    exports app.machine.enigmamachine;
}