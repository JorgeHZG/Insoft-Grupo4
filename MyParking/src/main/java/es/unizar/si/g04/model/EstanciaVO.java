package es.unizar.si.g04.model;

import java.sql.Timestamp;

public class EstanciaVO {
    // Hace falta poner el tipo de dato timestamp without timezone?
    // Y el tipo de dato character varying?
    private Timestamp fecha;
    private Timestamp hora_salida;
    private Timestamp hora_entrada;

    public EstanciaVO(Timestamp fecha, Timestamp hora_salida, Timestamp hora_entrada) {
        this.fecha = fecha;
        this.hora_entrada = hora_entrada;
        this.hora_salida = hora_salida;
    }

    // ---------------------------------------

    public Timestamp getEstanciaFecha() {
        return this.fecha;
    }

    public Timestamp getEstanciaHora_Salida() {
        return this.hora_salida;
    }

    public Timestamp getEstanciaHora_Entrada() {
        return this.hora_entrada;
    }

    // ---------------------------------------

    public void setEstanciaFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public void setEstanciaHora_Salida(Timestamp hora_salida) {
        this.hora_salida = hora_salida;
    }

    public void setEstanciaHora_Entrada(Timestamp hora_entrada) {
        this.hora_entrada = hora_entrada;
    }
}