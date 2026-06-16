package com.barrios.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Propietario extends Usuario {
    private String telefono;
    private Vivienda vivienda;
    private final List<AutorizacionVisita> autorizaciones = new ArrayList<>();

    public Propietario() {
    }

    public Propietario(Long id, String nombre, String email, String telefono) {
        super(id, nombre, null, email, null, "PROPIETARIO");
        this.telefono = telefono;
    }

    public Propietario(Long id, String nombre, String apellido, String email, String password, String telefono) {
        super(id, nombre, apellido, email, password, "PROPIETARIO");
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
