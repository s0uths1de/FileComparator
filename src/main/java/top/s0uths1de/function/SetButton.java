package top.s0uths1de.function;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import top.s0uths1de.ComparatorValue;
import top.s0uths1de.Main;
import top.s0uths1de.controller.ControllerMain;
import top.s0uths1de.core.FileComparator;
import top.s0uths1de.entity.FileEntity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SetButton {

    public static FileEntity fe;

    public SetButton() {
        fe = new FileEntity();
    }

    public static FileEntity setInfo(Button button, Stage stage) {
        FileEntity fe = new FileEntity();
        INIFileHandler ini = new INIFileHandler();
        try {
            ini.load(Permanently.getMainConfigFile().getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        button.setText("读取信息文件");
        button.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            if (fileChooser.showOpenDialog(stage)==null)
                return;
            fe.setInfo(fileChooser.showOpenDialog(stage));
            try {
                ini.setValue(Permanently.SECTION_CRITICAL, Permanently.LAST_TIME_FILE, fe.getInfo().getAbsolutePath());
                ini.save(Permanently.getMainConfigFile().getAbsolutePath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        setOnDragOver(button);
        button.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            if (db.hasFiles()) {
                if (!db.hasFiles())
                    return;
                for (File file : db.getFiles()) {
                    if (file != null) {
                        fe.setInfo(file);
                        try {
                            ini.setValue(Permanently.SECTION_CRITICAL, Permanently.LAST_TIME_FILE, fe.getInfo().getAbsolutePath());
                            ini.save(Permanently.getMainConfigFile().getAbsolutePath());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        return;
                    }
                }
            }
        });
        return fe;
    }

    public static void setExplorer(Button button, Stage stage, FileEntity fe) {
        INIFileHandler ini = new INIFileHandler();
        try {
            ini.load(Permanently.getMainConfigFile().getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        button.setText("读取作业");
        button.setOnAction(event -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            if (directoryChooser.showDialog(stage)==null)
                return;
            File folder = directoryChooser.showDialog(stage);
            sava(fe, ini, folder);
        });

        setOnDragOver(button);

        button.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            if (db.hasFiles()) {
                if (!db.hasFiles())
                    return;
                for (File file : db.getFiles()) {
                    sava(fe, ini, file);
                }
            }
        });
    }

    private static void sava(FileEntity fe, INIFileHandler ini, File file) {
        if (file != null) {
            fe.setHomework(file);
            try {
                ini.setValue(Permanently.SECTION_CRITICAL, Permanently.LAST_TIME_EXPLORER, fe.getHomework().getAbsolutePath());
                ini.save(Permanently.getMainConfigFile().getAbsolutePath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void setOnDragOver(Button explorerButton) {
        explorerButton.setOnDragOver(event -> {
            if (event.getDragboard().hasFiles()) event.acceptTransferModes(TransferMode.ANY);
        });
    }

    public static void setStart(FileEntity fe) {
        final String fileFormat = "{ID}{NAME}"; // TODO: allow user to modify this
        //final String fileFormat = "{ID}{NAME}.docx"; // TODO: ignore extension name at present
        INIFileHandler handler = new INIFileHandler();
        try {
            handler.load(Permanently.getMainConfigFile().getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String file = handler.getValue(Permanently.SECTION_CRITICAL, Permanently.LAST_TIME_FILE).replace("\"","");
        String explorer = handler.getValue(Permanently.SECTION_CRITICAL, Permanently.LAST_TIME_EXPLORER).replace("\"","");
        fe.setInfo(new File(file));
        fe.setHomework(new File(explorer));
        System.out.println(fe.getInfo().getAbsolutePath());
        System.out.println(fe.getHomework().getAbsolutePath());
        if (fe.getInfo() == null || fe.getHomework() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("错误");
            alert.setHeaderText(null);
            alert.setGraphic(null);
            if (fe.getInfo() == null && fe.getHomework() == null) alert.setContentText("Info and Homework is null");
            else if (fe.getHomework() == null) alert.setContentText("HomeWork is null");
            else alert.setContentText("Info is null");
            alert.showAndWait();
            return;
        }
        FileComparator comparator = new FileComparator(fe.getInfo(), fe.getHomework());
        List<List> lists = getLists(comparator, fileFormat);
        ControllerMain.setIsOneOrTwo(true);
        ControllerMain.setList(lists);
        setScene(false);
    }

    private static List<List> getLists(FileComparator comparator, String fileFormat) {
        Map<String, String> infoMap = comparator.getInfoMap();
        List<String> directoryList = comparator.getDirectoryList();
        List<String> id = comparator.getId();
        List<String> name = comparator.getName();
        List<String> correctList = new ArrayList<>();
        List<String> unpaidList = new ArrayList<>();
        List<String> idError = new ArrayList<>();
        List<String> nameError = new ArrayList<>();
        List<String> unknownList = new ArrayList<>(directoryList);
        infoMap.forEach((stuid, stuname) -> {
            if (stuid.equals("null") || stuname.equals("null")) {
                return;
            }
            String current = fileFormat.replace("{ID}", stuid).replace("{NAME}", stuname);
            if (current.contains("null"))
                System.out.println();
            if (directoryList.contains(current)) {
                correctList.add(current);
                unknownList.remove(current);
            } else if (id.contains(stuid) && !name.contains(stuname)) nameError.add(current);
            else if (name.contains(stuname)) idError.add(current);
            else unpaidList.add(current);
        });
        List<List> lists = new ArrayList<>() {{
            add(correctList);
            add(unpaidList);
            add(idError);
            add(nameError);
            add(unknownList);
        }};
        return lists;
    }

    public static void setScene(boolean isMain) {
        FXMLLoader resultFXML = new FXMLLoader(Main.class.getResource("/top/s0uths1de/filecomparator/fxmlui/main.fxml"));
        Pane resultUi = null;
        try {
            resultUi = resultFXML.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<Node> nodes = null;
        if (isMain)
            nodes = resultUi.getChildren().subList(0, 1);
        else
            nodes = resultUi.getChildren().subList(1, 2);
        Pane pane = new StackPane();
        pane.getChildren().addAll(nodes);
        Main.stage.setScene(new Scene(pane, ComparatorValue.WIDTH, ComparatorValue.HEIGHT));
        Main.stage.show();
    }

    public static void setUnrealized() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("错误");
        alert.setHeaderText(null);
        alert.setGraphic(null);
        alert.setContentText("未实现");
        alert.showAndWait();
    }
}
