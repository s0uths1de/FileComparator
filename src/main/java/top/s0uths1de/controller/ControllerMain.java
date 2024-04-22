package top.s0uths1de.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import top.s0uths1de.ComparatorValue;
import top.s0uths1de.entity.FileEntity;
import top.s0uths1de.function.INIFileHandler;
import top.s0uths1de.function.Permanently;
import top.s0uths1de.function.SetButton;
import top.s0uths1de.tools.Simplify;

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
    private Button showAll;

    @FXML
    private Button start;

    @FXML
    private Button about;

    @FXML
    private ListView<String> unpaidList;

    @FXML
    private ListView<String> other;

    private static FileEntity fe;
    private static INIFileHandler ini;

    private static boolean isOneOrTwo;

    private static List lists;

    @FXML
    void initialize() {
        Stage stage = new Stage();
        fe = new FileEntity();
        ini = new INIFileHandler();
        try {
            ini.load(Permanently.getMainConfigFile().getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SetButton.setInfo(readInfo, stage, fe,ini);
        SetButton.setExplorer(readExplorer, stage, fe,ini);
        start.setOnAction(event -> {
            SetButton.setStart(fe);
        });
        if (isOneOrTwo) {
            setList(false, this.other, 0, "已交");
            setList(false, this.unpaidList, 1, "未交");
        }
        showNameError.setOnAction(event -> {
            setList(true, this.other, 3, "Key错误");
        });
        showIdName.setOnAction(event -> {
            setList(true, this.other, 2, "Value错误");
        });
        showAll.setOnAction(event -> {
            setList(true, this.other, 0, "已交");
        });
        showUnknown.setOnAction(event -> {
            setList(true, this.other, 4, "未知");
        });
        back.setOnAction(event -> {
            ControllerMain.setIsOneOrTwo(false);
            SetButton.setScene(true);
        });
        replace.setOnAction(event -> {
            SetButton.setReplace(fe);
        });
        about.setOnAction(event -> {
            Image image = new Image(Simplify.urlToString(this.getClass(), "/top/s0uths1de/filecomparator/assets/icon.png").toString());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setGraphic(new ImageView(image));
            alert.setTitle("About");
            alert.setHeaderText(null);
            alert.setGraphic(null);
            Button copy = new Button("copy");
            alert.setContentText("Author:" + ComparatorValue.AUTHOR + "\n" + ComparatorValue.GITHUB_REPERTORY);
            alert.showAndWait();
        });
    }

    public void setList(boolean isClear, ListView<String> listView, int index, String description) {
        if (isClear)
            listView.getItems().clear();
        listView.getItems().clear();
        List<String> list = (ArrayList<String>) lists.get(index);
        listView.getItems().add(description + "\t人数：" + list.size());
        listView.getItems().addAll(list);
    }

    public static void setIsOneOrTwo(boolean isOneOrTwo) {
        ControllerMain.isOneOrTwo = isOneOrTwo;
    }

    public static void setList(List list) {
        ControllerMain.lists = list;
    }

}
