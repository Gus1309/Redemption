package com.barrios.estado;

import com.barrios.modelo.Reclamo;

/**
 * Estado concreto del patron State.
 *
 * Representa un reclamo tomado o en atencion. Dentro del flujo principal de la
 * demo puede avanzar al estado RESUELTO.
 */
public class EstadoEnProceso implements IEstadoReclamo {

    @Override
    public void avanzar(Reclamo reclamo) {
        reclamo.setEstado(new EstadoResuelto());
    }

    @Override
    public String getNombre() {
        return "EN_PROCESO";
    }
}
