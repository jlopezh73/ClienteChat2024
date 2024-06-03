package ejemplos2024.clientechat2024.ui;

import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;

public class ContenedorCliente extends BorderPane {
    private TitledPane paneConexion;
    private TitledPane paneMensajes;
    private TitledPane paneMensaje;

    private Label etiServidor;
    private Label etiPuerto;
    private Label etiUsuario;
    private Label etiPassword;
    private TextField txtServidor;
    private TextField txtPuerto;
    private TextField txtUsuario;
    private PasswordField txtPassword;
    private TextArea txtMensajes;
    private TextField txtMensaje;
    private Button btnConectar;
    private Button btnEnviarMensaje;
    public ContenedorCliente() {
        crearComponentes();
    }

    private void crearComponentes() {
        //Crear Panel de Conexión
        paneConexion = new TitledPane();
        paneConexion.setText("Conexión al servidor");
        paneMensajes = new TitledPane();
        paneMensajes.setText("Mensajes recibidos");
        paneMensaje = new TitledPane();
        paneMensaje.setText("Mensaje a enviar");

        etiServidor = new Label("Servidor:");
        txtServidor = new TextField("127.0.0.1");
        etiPuerto = new Label("Puerto:");
        txtPuerto = new TextField("4500");
        etiUsuario = new Label("Usuario:");
        txtUsuario = new TextField("administrador@cursos.uv.mx");
        etiPassword = new Label("Password:");
        txtPassword = new PasswordField();
        txtPassword.setText("alfaomega");
        btnConectar = new Button("Conectar");

        HBox contPaneConexion = new HBox();
        contPaneConexion.getChildren().addAll(etiServidor, txtServidor, etiPuerto, txtPuerto,
                  etiUsuario, txtUsuario, etiPassword, txtPassword, btnConectar);
        paneConexion.setContent(contPaneConexion);
        setTop(paneConexion);

        txtMensajes = new TextArea();
        txtMensajes.setEditable(false);
        txtMensajes.setMinWidth(950);
        txtMensajes.setMinHeight(330);
        paneMensajes.setContent(new ScrollPane(txtMensajes));
        paneMensajes.setMinHeight(360);

        txtMensaje = new TextField();
        txtMensaje.setPromptText("Escriba su mensaje a enviar...");
        txtMensaje.setMinWidth(900);
        btnEnviarMensaje = new Button("Enviar");

        setCenter(paneMensajes);

        HBox contPaneMensaje = new HBox();
        contPaneMensaje.getChildren().addAll(txtMensaje, btnEnviarMensaje);
        paneMensaje.setContent(contPaneMensaje);

        setBottom(paneMensaje);
    }
}
