package com.barrios.modelo;

public class PersonalSeguridad extends Usuario {
    private String turno;

    public PersonalSeguridad() {
    }

    public PersonalSeguridad(Long id, String nombre, String email, String turno) {
        super(id, nombre, null, email, null, "PERSONAL_SEGURIDAD");
        this.turno = turno;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
}
