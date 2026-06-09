package com.barrios.modelo;

public class Administrador extends Usuario {
    private String area;

    public Administrador() {
    }

    public Administrador(Long id, String nombre, String email, String area) {
        super(id, nombre, email);
        this.area = area;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
