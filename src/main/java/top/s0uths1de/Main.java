package top.s0uths1de;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import top.s0uths1de.entity.FileEntity;
import top.s0uths1de.function.SetButton;
import top.s0uths1de.tools.Simplify;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class Main extends Application {

    static FileEntity fe;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader mainUi = new FXMLLoader(getClass().getResource("/top/s0uths1de/filecomparator/fxmlui/main.fxml"));
        Pane root = mainUi.load();
        List<Node> nodes = root.getChildren().subList(0,1);
        Pane subscene = new StackPane();
        subscene.getChildren().addAll(nodes);
        ((Button)(subscene.lookup("#a"))).setOnAction(event -> {
            FXMLLoader ui = new FXMLLoader(getClass().getResource("/top/s0uths1de/filecomparator/fxmlui/main.fxml"));
            Pane c =null;
            try {
                c = ui.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            List<Node> a = c.getChildren().subList(1,2);
            Pane b = new StackPane();
            b.getChildren().addAll(a);
            primaryStage.setScene(new Scene(b,854,480));
            SetButton.setStart(this,primaryStage,new FileEntity(new File("F:\\queryhomework\\作业(2).csv"),new File("F:\\queryhomework\\实验报告一")));
        });
        primaryStage.setScene(new Scene(subscene,854,480));
        Image image = new Image(Simplify.urlToString(this.getClass(), "/top/s0uths1de/filecomparator/assets/icon.png").toString());
        primaryStage.getIcons().setAll(image);
        primaryStage.setTitle(ComparatorValue.TITLE);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
