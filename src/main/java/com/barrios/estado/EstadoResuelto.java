package com.barrios.estado;

import com.barrios.modelo.Reclamo;

public class EstadoResuelto implements IEstadoReclamo {
    @Override
    public void avanzar(Reclamo reclamo) {
        // TODO: mantener estado final o definir comportamiento.
    }

    @Override
    public String getNombre() {
        return "Resuelto";
    }
}
