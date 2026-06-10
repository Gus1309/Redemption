package com.barrios.modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservaAmenidad {
    private Long id;
    private Amenidad amenidad;
    private Propietario propietario;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String estado;

    public ReservaAmenidad() {
    }

    public ReservaAmenidad(Long id, Amenidad amenidad, Propietario propietario, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, String estado) {
        this.id = id;
        this.amenidad = amenidad;
        this.propietario = propietario;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.estado = estado;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Amenidad getAmenidad() { return amenidad; }
    public void setAmenidad(Amenidad amenidad) { this.amenidad = amenidad; }

    public Propietario getPropietario() { return propietario; }
    public void setPropietario(Propietario propietario) { this.propietario = propietario; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public LocalTime getHoraInicio() { return horaInicio; }
    public void setHoraInicio(LocalTime horaInicio) { this.horaInicio = horaInicio; }

    public LocalTime getHoraFin() { return horaFin; }
    public void setHoraFin(LocalTime horaFin) { this.horaFin = horaFin; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}