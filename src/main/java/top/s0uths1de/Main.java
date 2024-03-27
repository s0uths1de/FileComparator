package top.s0uths1de;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import top.s0uths1de.tools.Simplify;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader mainUi = new FXMLLoader(getClass().getResource("/top/s0uths1de/filecomparator/fxmlui/main.fxml"));
        Parent root = mainUi.load();
        primaryStage.setScene(new Scene(root));
        Image image = new Image(Simplify.urlToString(this.getClass(), "/top/s0uths1de/filecomparator/assets/icon.png").toString());
        primaryStage.getIcons().setAll(image);
        primaryStage.setTitle(ComparatorValue.TITLE);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
