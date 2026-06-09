package com.barrios.modelo;

import java.time.LocalDate;

public class AutorizacionVisita {
    private Long id;
    private Visitante visitante;
    private Propietario propietario;
    private LocalDate fecha;
    private String estado;

    public AutorizacionVisita() {
    }

    public AutorizacionVisita(Long id, Visitante visitante, Propietario propietario, LocalDate fecha, String estado) {
        this.id = id;
        this.visitante = visitante;
        this.propietario = propietario;
        this.fecha = fecha;
        this.estado = estado;
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

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
