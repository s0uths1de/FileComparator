package top.s0uths1de.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ControllerResult {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button back;

    @FXML
    private ListView<?> error;

    @FXML
    private ListView<?> info;

    @FXML
    private Button replace;

    @FXML
    private Pane result;

    @FXML
    private Button setUnifiedName;

    @FXML
    private Button showIdError;

    @FXML
    private Button showNameError;

    @FXML
    private Button showUnknown;

    @FXML
    private ListView showUnpaid;

    @FXML
    private VBox vBox;

    @FXML
    void initialize() {

    }

}
