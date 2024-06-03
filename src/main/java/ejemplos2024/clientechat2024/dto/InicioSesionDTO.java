package ejemplos2024.clientechat2024.dto;

public class InicioSesionDTO {
    private String correoElectronico;
    private String password;

    public InicioSesionDTO() {
    }

    public InicioSesionDTO(String correoElectronico, String password) {
        this.correoElectronico = correoElectronico;
        this.password = password;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
