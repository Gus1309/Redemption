package com.barrios.modelo;

import java.util.ArrayList;
import java.util.List;

public class Barrio {
    private Long id;
    private String nombre;
    private String descripcion;
    private List<Vivienda> viviendas;
    private List<Amenidad> amenidades;

    public Barrio() {
        this.viviendas = new ArrayList<>();
        this.amenidades = new ArrayList<>();
    }

    public Barrio(Long id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.viviendas = new ArrayList<>();
        this.amenidades = new ArrayList<>();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public List<Vivienda> getViviendas() { return viviendas; }
    public void setViviendas(List<Vivienda> viviendas) { this.viviendas = viviendas; }

    public List<Amenidad> getAmenidades() { return amenidades; }
    public void setAmenidades(List<Amenidad> amenidades) { this.amenidades = amenidades; }
}