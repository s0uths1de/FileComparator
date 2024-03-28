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
    private Button a;

    @FXML
    private Button b;

    @FXML
    private Pane main;

    @FXML
    private Pane result;

    @FXML
    void initialize() throws IOException {
        Stage stage = new Stage();
//        FileEntity fileEntity = new FileEntity();
//        SetButton.setInfo(readInfo,stage,fileEntity);
//        SetButton.setExplorer(readHomework,stage,fileEntity);
//        SetButton.setStart(start,stage,fileEntity);
//        softInfo.setText(ComparatorValue.TITLE);

//            FXMLLoader mainUi = new FXMLLoader(getClass().getResource("/top/s0uths1de/filecomparator/fxmlui/main.fxml"));
//            Pane root = null;
//            root = mainUi.load();
//            List<Node> nodes = root.getChildren().subList(1,2);
//            Pane subscene = new StackPane();
//            subscene.getChildren().addAll(nodes);
//            stage.setScene(new Scene(subscene,854,480));
    }
}
