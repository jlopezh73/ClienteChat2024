package ejemplos2024.clientechat2024.dto;

public class RespuestaInicioSesionDTO {
    private boolean correcto;
    private String mensaje;
    private int idUsuario;
    private String nombre;

    public RespuestaInicioSesionDTO() {
    }

    public RespuestaInicioSesionDTO(boolean correcto, String mensaje, int idUsuario, String nombre) {
        this.correcto = correcto;
        this.mensaje = mensaje;
        this.idUsuario = idUsuario;
        this.nombre = nombre;
    }

    public boolean isCorrecto() {
        return correcto;
    }

    public void setCorrecto(boolean correcto) {
        this.correcto = correcto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
