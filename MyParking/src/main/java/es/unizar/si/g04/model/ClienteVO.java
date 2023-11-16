package es.unizar.si.g04.model;

public class ClienteVO {
    private String dni;
    private String password;
    private String nombre;
    private String apellido;

    public ClienteVO(String dni, String password, String nombre, String apellido) {
        this.dni = dni;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    // getters
    public String getDni() {
        return this.dni;
    }

    public String getPassword() {
        return this.password;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}