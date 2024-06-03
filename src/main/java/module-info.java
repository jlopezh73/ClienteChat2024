module ejemplos2024.clientechat2024 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;

    opens ejemplos2024.clientechat2024 to javafx.fxml;
    exports ejemplos2024.clientechat2024;
}