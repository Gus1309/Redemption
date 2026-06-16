package com.barrios.modelo;

public class Vivienda {
    private Long id;
    private String numero;
    private String calle;
    private Propietario propietario;
    private Barrio barrio;

    public Vivienda() {
    }

    public Vivienda(Long id, String direccion, Propietario propietario) {
        this(id, direccion, null, propietario, null);
    }

    public Vivienda(Long id, String numero, String calle, Propietario propietario, Barrio barrio) {
        this.id = id;
        this.numero = numero;
        this.calle = calle;
        this.propietario = propietario;
        this.barrio = barrio;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public String getDireccion() {
        if (calle == null || calle.isBlank()) {
            return numero;
        }
        return calle + " " + numero;
    }

    public void setDireccion(String direccion) {
        this.numero = direccion;
        this.calle = null;
    }

    public String getCalle() { return calle; }
    public void setCalle(String calle) { this.calle = calle; }

    public Propietario getPropietario() { return propietario; }
    public void setPropietario(Propietario propietario) { this.propietario = propietario; }

    public Barrio getBarrio() { return barrio; }
    public void setBarrio(Barrio barrio) { this.barrio = barrio; }
}
