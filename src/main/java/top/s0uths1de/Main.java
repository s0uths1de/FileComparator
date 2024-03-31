package top.s0uths1de;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import top.s0uths1de.controller.ControllerMain;
import top.s0uths1de.entity.FileEntity;
import top.s0uths1de.function.SetButton;
import top.s0uths1de.tools.Simplify;

import java.util.ArrayList;
import java.util.List;


public class Main extends Application {

    static FileEntity fe;
    static List<List> lists;

    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader mainFXML = new FXMLLoader(getClass().getResource("/top/s0uths1de/filecomparator/fxmlui/main.fxml"));
        Pane root = mainFXML.load();
        List<Node> nodes = root.getChildren().subList(0, 1);
        Pane subscene = new StackPane();
        subscene.getChildren().addAll(nodes);
//        ((Button) (subscene.lookup("#start"))).setOnAction(event -> {
//            fe = ControllerMain.getFe();
//            SetButton.setStart(this, primaryStage, fe);
//        });
        primaryStage.setScene(new Scene(subscene, ComparatorValue.WIDTH, ComparatorValue.HEIGHT));
        Image image = new Image(Simplify.urlToString(this.getClass(), "/top/s0uths1de/filecomparator/assets/icon.png").toString());
        primaryStage.getIcons().setAll(image);
        primaryStage.setTitle(ComparatorValue.TITLE);
        primaryStage.setResizable(false);
        primaryStage.show();
        stage=primaryStage;
    }

    public static List getList() {
        return lists;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
