package es.unizar.si.g04.model;

import java.time.LocalDateTime; // Usar LocalDateTime without time zone

public class EstanciaVO {
    // Hace falta poner el tipo de dato timestamp without timezone?
    // Y el tipo de dato character varying?
    private LocalDateTime fecha;
    private LocalDateTime hora_salida;
    private LocalDateTime hora_entrada;

    public EstanciaVO(LocalDateTime fecha, LocalDateTime hora_salida, LocalDateTime hora_entrada) {
        this.fecha = fecha;
        this.hora_entrada = hora_entrada;
        this.hora_salida = hora_salida;
    }

    // ---------------------------------------

    public LocalDateTime getEstanciaFecha() {
        return this.fecha;
    }

    public LocalDateTime getEstanciaHora_Salida() {
        return this.hora_salida;
    }

    public LocalDateTime getEstanciaHora_Entrada() {
        return this.hora_entrada;
    }

    // ---------------------------------------

    public void setEstanciaFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public void setEstanciaHora_Salida(LocalDateTime hora_salida) {
        this.hora_salida = hora_salida;
    }

    public void setEstanciaHora_Entrada(LocalDateTime hora_entrada) {
        this.hora_entrada = hora_entrada;
    }
}