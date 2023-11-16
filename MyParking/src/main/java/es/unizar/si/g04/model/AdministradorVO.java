package es.unizar.si.g04.model;

public class AdministradorVO {
    private String dni;
    private String contrasegna;

    public AdministradorVO(String dni, String contrasegna) {
        this.dni = dni;
        this.contrasegna = contrasegna;
    }

    public String getDni() {
        return this.dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getContrasegna() {
        return this.contrasegna;
    }

    public void setContrasegna(String contrasegna) {
        this.contrasegna = contrasegna;
    }
}