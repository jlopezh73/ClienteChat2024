module ejemplos2024.clientechat2024 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;

    opens ejemplos2024.clientechat2024 to javafx.fxml;
    opens ejemplos2024.clientechat2024.dto to com.google.gson;
    exports ejemplos2024.clientechat2024;
}