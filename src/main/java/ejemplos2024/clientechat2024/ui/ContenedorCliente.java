package ejemplos2024.clientechat2024.ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ejemplos2024.clientechat2024.dto.InicioSesionDTO;
import ejemplos2024.clientechat2024.dto.MensajeDTO;
import ejemplos2024.clientechat2024.dto.RespuestaInicioSesionDTO;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.InetSocketAddress;
import java.net.Socket;

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
    private Socket servidor;
    private BufferedReader entrada;
    private BufferedWriter salida;
    private Type tipoMensaje;
    private Type tipoRespuestaInicioSesion;
    private Gson gson;
    private final String FORMATO_FECHA = "dd/MM/yyyy";
    private boolean dejarEscuchar = true;
    private RespuestaInicioSesionDTO respuesta;
    public ContenedorCliente() {
        crearComponentes();
        tipoMensaje = new TypeToken<MensajeDTO>(){}.getType();
        tipoRespuestaInicioSesion = new TypeToken<RespuestaInicioSesionDTO>(){}.getType();
        gson = new GsonBuilder()
                .setDateFormat(FORMATO_FECHA)
                .create();
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
        btnConectar.setOnAction(evt -> {
            if (btnConectar.getText().equals("Conectar"))
                conectarServidor();
            else
                desconectarse();
        });

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
        btnEnviarMensaje.setOnAction(evt -> {
            enviarMensaje();
        });

        setCenter(paneMensajes);

        HBox contPaneMensaje = new HBox();
        contPaneMensaje.getChildren().addAll(txtMensaje, btnEnviarMensaje);
        paneMensaje.setContent(contPaneMensaje);

        setBottom(paneMensaje);
        habilitar(false);
    }

    private void desconectarse() {
        try {
            dejarEscuchar = true;
            habilitar(false);
            btnConectar.setText("Conectar");
            entrada.close();
            salida.close();
            servidor.close();

        } catch (Exception e) {

        }
    }

    private void habilitar(boolean hab) {
        txtServidor.setDisable(hab);
        txtPuerto.setDisable(hab);
        txtUsuario.setDisable(hab);
        txtPassword.setDisable(hab);

        txtMensaje.setDisable(!hab);
        btnEnviarMensaje.setDisable(!hab);
    }

    private void enviarMensaje() {
        String sMensaje = txtMensaje.getText();
        if (sMensaje.trim().length() > 0) {
            try {
                MensajeDTO mensaje = new MensajeDTO();
                mensaje.setIdUsuario(respuesta.getIdUsuario());
                mensaje.setUsuario(respuesta.getNombre());
                mensaje.setMensaje(sMensaje);

                String mensajeJSON = gson.toJson(mensaje);
                salida.write(mensajeJSON + "\n");
                salida.flush();

                txtMensaje.setText("");
                txtMensaje.requestFocus();
            } catch (Exception e) {

            }
        }
    }

    private void conectarServidor() {
        try {
            String direccionServidor = txtServidor.getText();
            int puerto = Integer.parseInt(txtPuerto.getText());
            servidor = new Socket();
            servidor.connect(new InetSocketAddress(direccionServidor, puerto));
            entrada = new BufferedReader(
                    new InputStreamReader(servidor.getInputStream()));
            salida = new BufferedWriter(
                    new OutputStreamWriter(servidor.getOutputStream()));

            String usuario = txtUsuario.getText();
            String password = txtPassword.getText();
            InicioSesionDTO inicioSesion = new InicioSesionDTO();
            inicioSesion.setCorreoElectronico(usuario);
            inicioSesion.setPassword(password);
            String cadInicioSesion=gson.toJson(inicioSesion);

            salida.write(cadInicioSesion+"\n");
            salida.flush();

            String respuestaJSON = entrada.readLine();
            respuesta = gson.fromJson(respuestaJSON,
                      tipoRespuestaInicioSesion);
            if (respuesta.isCorrecto()) {
                dejarEscuchar = false;
                HiloEscucha hilo = new HiloEscucha();
                hilo.start();

                habilitar(true);
                btnConectar.setText("Desconectar...");
            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error");
                alerta.setHeaderText("Datos de conexión erroenos");
                alerta.setContentText("El usuario o la contraseña proporcionada son inválidos");
                alerta.show();
            }


        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText("Imposible conectarse al servidor");
            alerta.setContentText(e.toString());
            alerta.show();
        }
    }

    class HiloEscucha extends Thread {
        public void run() {
            while(!dejarEscuchar) {
                try {
                    String mensajeJSON = entrada.readLine();
                    MensajeDTO mensaje = gson.fromJson(mensajeJSON,
                            tipoMensaje);
                    txtMensajes.appendText(mensaje.getUsuario()+"> "+mensaje.getMensaje()+"\n");
                } catch (Exception e) {

                }
            }
        }
    }
}
