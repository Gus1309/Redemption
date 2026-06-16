package com.barrios.modelo;

import com.barrios.estado.EstadoPendiente;
import com.barrios.estado.IEstadoReclamo;

import java.time.LocalDate;

public class Reclamo {
    private Long id;
    private String descripcion;
    private LocalDate fecha;
    private IEstadoReclamo estado;

    public Reclamo() {
        this.estado = new EstadoPendiente();
    }

    public Reclamo(Long id, String descripcion, LocalDate fecha, IEstadoReclamo estado) {
        this.id = id;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.estado = estado;
    }

    public void avanzarEstado() {
        estado.avanzar(this);
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

    public IEstadoReclamo getEstado() {
        return estado;
    }

    public void setEstado(IEstadoReclamo estado) {
        this.estado = estado;
    }
}
