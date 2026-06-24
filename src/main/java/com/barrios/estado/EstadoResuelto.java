package com.barrios.estado;

import com.barrios.modelo.Reclamo;


public class EstadoResuelto implements IEstadoReclamo {

    @Override
    public void avanzar(Reclamo reclamo) {
        reclamo.setEstado(this);
    }

    @Override
    public String getNombre() {
        return "RESUELTO";
    }
}
