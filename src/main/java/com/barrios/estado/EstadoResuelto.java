package com.barrios.estado;

import com.barrios.modelo.Reclamo;

/**
 * Estado concreto del patron State.
 *
 * Representa un reclamo finalizado. En el flujo principal de la demo no avanza
 * a otro estado, por eso mantiene el mismo estado al solicitar avanzar.
 */
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
