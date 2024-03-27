module top.s0uths1de.filecomparator {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens top.s0uths1de.filecomparator to javafx.fxml;
    exports top.s0uths1de;
    opens top.s0uths1de.controller to javafx.fxml;
}