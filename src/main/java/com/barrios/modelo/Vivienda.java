package com.barrios.modelo;

public class Vivienda {
    private Long id;
    private String direccion;
    private Propietario propietario;

    public Vivienda() {
    }

    public Vivienda(Long id, String direccion, Propietario propietario) {
        this.id = id;
        this.direccion = direccion;
        this.propietario = propietario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }
}
