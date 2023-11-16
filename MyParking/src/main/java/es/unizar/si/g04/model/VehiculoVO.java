package es.unizar.si.g04.model;

/*
    Tabla vehículo
*/

public class VehiculoVO {
    private String matricula;
    private String cliente;
    private String tipo;

    // Constructor:
    public VehiculoVO(String mat, String cli, String tipo) {
        this.matricula = mat;
        this.cliente = cli;
        this.tipo = tipo;
    }

    // getters
    public String getMatricula() {
        return this.matricula;
    }

    public String getCliente() {
        return this.cliente;
    }

    public String getTipo() {
        return this.tipo;
    }

    // Setters
    public void setMatricula(String mat) {
        this.matricula = mat;
    }

    public void setCliente(String cli) {
        this.cliente = cli;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}