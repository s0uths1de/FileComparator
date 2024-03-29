package top.s0uths1de.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import top.s0uths1de.ComparatorValue;
import top.s0uths1de.entity.FileEntity;
import top.s0uths1de.function.SetButton;

public class ControllerMain {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button back;

    @FXML
    private Pane main;

    @FXML
    private Button readExplorer;

    @FXML
    private Button readInfo;

    @FXML
    private Button rename;

    @FXML
    private Button replace;

    @FXML
    private Pane result;

    @FXML
    private Button showIdName;

    @FXML
    private Button showNameError;

    @FXML
    private Button showUnknown;

    @FXML
    private Button showUnpaid;

    @FXML
    private Button start;

    private static FileEntity fe;

    public static FileEntity getFe() {
        return fe;
    }

    public static void setFe(FileEntity fe) {
        ControllerMain.fe = fe;
    }

    @FXML
    void initialize() throws IOException {
        Stage stage = new Stage();
        fe = SetButton.setInfo(readInfo, stage);
        SetButton.setExplorer(readExplorer, stage, fe);
    }
}
