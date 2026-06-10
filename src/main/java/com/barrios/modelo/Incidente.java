package com.barrios.modelo;

import com.barrios.notificacion.IObservable;
import com.barrios.notificacion.IObserver;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Incidente implements IObservable {

    private Long id;
    private String descripcion;
    private LocalDate fecha;
    private String estado;

    private final List<IObserver> observers = new ArrayList<>();

    public Incidente() {
    }

    public Incidente(Long id, String descripcion, LocalDate fecha, String estado) {
        this.id = id;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.estado = estado;
    }

    public void cambiarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
        notificarObserver(nuevoEstado);
    }

    // IObservable

    @Override
    public void agregarObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removerObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notificarObserver(String evento) {
        for (IObserver observer : observers) {
            observer.actualizar(evento, this);
        }
    }

    // Getters y Setters

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