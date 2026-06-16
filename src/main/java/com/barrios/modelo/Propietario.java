package com.barrios.modelo;

public class Propietario extends Usuario {
    private String telefono;

    public Propietario() {
    }

    public Propietario(Long id, String nombre, String email, String telefono) {
        super(id, nombre, email);
        this.telefono = telefono;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public Rol getRol() {
        return Rol.PROPIETARIO;
    }
}
