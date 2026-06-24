package com.barrios.estado;

import com.barrios.modelo.Reclamo;


public class EstadoPendiente implements IEstadoReclamo {

    @Override
    public void avanzar(Reclamo reclamo) {
        reclamo.setEstado(new EstadoEnProceso());
    }

    @Override
    public String getNombre() {
        return "PENDIENTE";
    }
}
