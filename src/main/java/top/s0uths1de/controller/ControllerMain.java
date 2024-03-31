package top.s0uths1de.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import top.s0uths1de.Main;
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
    private Button showAll;

    @FXML
    private Button start;

    @FXML
    private ListView<String> unpaidList;

    @FXML
    private ListView<String> other;

    private static FileEntity fe;

    private static boolean isOneOrTwo;

    private static List lists;

    @FXML
    void initialize() {
        Stage stage = new Stage();
        fe = SetButton.setInfo(readInfo, stage);
        SetButton.setExplorer(readExplorer, stage, fe);
        start.setOnAction(event -> {
            SetButton.setStart(fe);
        });
        if (isOneOrTwo) {
            setList(false,this.other, 0, "已交作业");
            setList(false,this.unpaidList, 1, "未交作业");
        }
        showNameError.setOnAction(event -> {
            setList(true,this.other, 3, "名字错误");
        });
        showIdName.setOnAction(event -> {
            setList(true,this.other, 2, "学号错误");
        });
        showAll.setOnAction(event -> {
            setList(true,this.other, 0, "已交作业");
        });
        showUnknown.setOnAction(event -> {
            setList(true,this.other, 4, "未知学生");
        });
        back.setOnAction(event -> {
            System.out.println("aaaaa");
            ControllerMain.setIsOneOrTwo(false);
            SetButton.setScene(true);
        });
        readInfo.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("错误");
            alert.setHeaderText(null);
            alert.setGraphic(null);
            alert.setContentText("未实现");
            alert.showAndWait();
        });
        rename.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("错误");
            alert.setHeaderText(null);
            alert.setGraphic(null);
            alert.setContentText("未实现");
            alert.showAndWait();
        });
    }

    public void setList(boolean isClear ,ListView<String> listView, int index, String description) {
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
