package com.barrios.estado;

import com.barrios.modelo.Reclamo;

/**
 * Estado concreto del patron State.
 *
 * Representa un reclamo recien creado. Dentro del flujo principal de la demo
 * puede avanzar al estado EN_PROCESO.
 */
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
