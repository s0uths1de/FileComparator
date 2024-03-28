package top.s0uths1de.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
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
    private HBox box;

    @FXML
    private Button readHomework;

    @FXML
    private Button readInfo;

    @FXML
    private Button setting;

    @FXML
    private Text softInfo;

    @FXML
    private Button start;

    @FXML
    void initialize() {
        Stage stage = new Stage();
        FileEntity fileEntity = new FileEntity();
        SetButton.setInfo(readInfo,stage,fileEntity);
        SetButton.setExplorer(readHomework,stage,fileEntity);
        SetButton.setStart(start,stage,fileEntity);
        softInfo.setText(ComparatorValue.TITLE);
    }
}
