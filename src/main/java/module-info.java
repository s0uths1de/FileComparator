module top.s0uths1de {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;

    exports top.s0uths1de;
    opens top.s0uths1de.controller to javafx.fxml;
}