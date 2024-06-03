package ejemplos2024.clientechat2024.dto;

public class MensajeDTO {
    private int idUsuario;
    private String usuario;
    private String mensaje;

    public MensajeDTO() {
    }

    public MensajeDTO(int idUsuario, String usuario, String mensaje) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.mensaje = mensaje;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
