package com.barrios.modelo;

public class Tecnico extends Usuario {
    private String especialidad;

    public Tecnico() {
    }

    public Tecnico(Long id, String nombre, String email, String especialidad) {
        super(id, nombre, email);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public Rol getRol() {
        return Rol.TECNICO;
    }
}
