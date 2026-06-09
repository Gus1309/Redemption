package com.barrios.modelo;

import java.time.LocalDateTime;

public class RegistroAcceso {
    private Long id;
    private Visitante visitante;
    private LocalDateTime fecha;
    private String tipoAcceso;

    public RegistroAcceso() {
    }

    public RegistroAcceso(Long id, Visitante visitante, LocalDateTime fecha, String tipoAcceso) {
        this.id = id;
        this.visitante = visitante;
        this.fecha = fecha;
        this.tipoAcceso = tipoAcceso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Visitante getVisitante() {
        return visitante;
    }

    public void setVisitante(Visitante visitante) {
        this.visitante = visitante;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getTipoAcceso() {
        return tipoAcceso;
    }

    public void setTipoAcceso(String tipoAcceso) {
        this.tipoAcceso = tipoAcceso;
    }
}
