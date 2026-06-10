package com.barrios.modelo;

import java.util.ArrayList;
import java.util.List;

public class Propietario extends Usuario {
    private String telefono;
    private Vivienda vivienda;
    private List<AutorizacionVisita> autorizaciones;

    public Propietario() {
        this.autorizaciones = new ArrayList<>();
    }

    public Propietario(Long id, String nombre, String apellido, String email, String password, String telefono) {
        super(id, nombre, apellido, email, password, "PROPIETARIO");
        this.telefono = telefono;
        this.autorizaciones = new ArrayList<>();
    }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public Vivienda getVivienda() { return vivienda; }
    public void setVivienda(Vivienda vivienda) { this.vivienda = vivienda; }

    public List<AutorizacionVisita> getAutorizaciones() { return autorizaciones; }
    public void setAutorizaciones(List<AutorizacionVisita> autorizaciones) { this.autorizaciones = autorizaciones; }
}