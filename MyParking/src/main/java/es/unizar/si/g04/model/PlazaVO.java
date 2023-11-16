package es.unizar.si.g04.model;

public class PlazaVO {
    private int numero;
    private boolean estado;
    private String tipo;

    public PlazaVO(int numero, boolean estado, String tipo) {
        this.numero = numero;
        this.estado = estado;
        this.tipo = tipo;
    }

    public int getNumeroPlaza() {
        return this.numero;
    }

    public void setNumeroPlaza(int numero) {
        this.numero = numero;
    }

    public boolean getEstadoPlaza() {
        return this.estado;
    }

    public void setEstadoPlaza(boolean estado) {
        this.estado = estado;
    }

    public String getTipoPLaza() {
        return this.tipo;
    }

    public void setTipoPlaza(String tipo) {
        this.tipo = tipo;
    }

}