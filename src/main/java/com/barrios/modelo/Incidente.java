package com.barrios.modelo;

import java.time.LocalDate;

public class Incidente {
    private Long id;
    private String descripcion;
    private LocalDate fecha;
    private String estado;

    public Incidente() {
    }

    public Incidente(Long id, String descripcion, LocalDate fecha, String estado) {
        this.id = id;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.estado = estado;
    }

    public void cambiarEstado(String nuevoEstado) {
        // TODO: notificar observadores cuando se implemente el flujo.
        this.estado = nuevoEstado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
