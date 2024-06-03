package ejemplos2024.clientechat2024;

import ejemplos2024.clientechat2024.ui.ContenedorCliente;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ClienteChatApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ContenedorCliente root = new ContenedorCliente();
        Scene scene = new Scene(root, 1000, 500);
        stage.setTitle("Cliente Chat");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}