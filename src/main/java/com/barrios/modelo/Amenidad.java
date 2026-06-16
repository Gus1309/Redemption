package com.barrios.modelo;

public class Amenidad {
    private Long id;
    private String nombre;
    private String descripcion;
    private int capacidad;
    private boolean disponible;

    public Amenidad() {
    }

    public Amenidad(Long id, String nombre, String descripcion) {
        this(id, nombre, descripcion, 0, true);
    }

    public Amenidad(Long id, String nombre, String descripcion, int capacidad, boolean disponible) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.capacidad = capacidad;
        this.disponible = disponible;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }

    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
}
