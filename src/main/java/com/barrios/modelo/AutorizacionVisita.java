package com.barrios.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AutorizacionVisita {
    private Long id;
    private Visitante visitante;
    private Propietario propietario;
    private LocalDate fechaDesde;
    private LocalDate fechaHasta;
    private String estado;

    public AutorizacionVisita() {
    }

    public AutorizacionVisita(Long id, Visitante visitante, Propietario propietario, LocalDate fechaDesde, LocalDate fechaHasta, String estado) {
        this.id = id;
        this.visitante = visitante;
        this.propietario = propietario;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.estado = estado;
    }

    public boolean esValida(LocalDateTime fechaHora) {
        LocalDate fecha = fechaHora.toLocalDate();
        return !fecha.isBefore(fechaDesde) && !fecha.isAfter(fechaHasta);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Visitante getVisitante() { return visitante; }
    public void setVisitante(Visitante visitante) { this.visitante = visitante; }

    public Propietario getPropietario() { return propietario; }
    public void setPropietario(Propietario propietario) { this.propietario = propietario; }

    public LocalDate getFechaDesde() { return fechaDesde; }
    public void setFechaDesde(LocalDate fechaDesde) { this.fechaDesde = fechaDesde; }

    public LocalDate getFechaHasta() { return fechaHasta; }
    public void setFechaHasta(LocalDate fechaHasta) { this.fechaHasta = fechaHasta; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}