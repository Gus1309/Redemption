package com.barrios.modelo;

import java.time.LocalDate;

public class ReservaAmenidad {
    private Long id;
    private Amenidad amenidad;
    private Propietario propietario;
    private LocalDate fecha;
    private String estado;

    public ReservaAmenidad() {
    }

    public ReservaAmenidad(Long id, Amenidad amenidad, Propietario propietario, LocalDate fecha, String estado) {
        this.id = id;
        this.amenidad = amenidad;
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

    public Amenidad getAmenidad() {
        return amenidad;
    }

    public void setAmenidad(Amenidad amenidad) {
        this.amenidad = amenidad;
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
