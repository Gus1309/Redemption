package com.barrios.modelo;

public class Administrador extends Usuario {
    private String area;

    public Administrador() {
    }

    public Administrador(Long id, String nombre, String email, String area) {
        super(id, nombre, null, email, null, "ADMINISTRADOR");
        this.area = area;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public Rol getRol() {
        return Rol.ADMINISTRADOR;
    }
}
