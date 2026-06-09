package com.barrios.modelo;

import java.time.LocalDate;

public class Novedad {
    private Long id;
    private String descripcion;
    private LocalDate fecha;

    public Novedad() {
    }

    public Novedad(Long id, String descripcion, LocalDate fecha) {
        this.id = id;
        this.descripcion = descripcion;
        this.fecha = fecha;
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
}
