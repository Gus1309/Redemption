package com.barrios.estado;

import com.barrios.modelo.Reclamo;

/**
 * Contrato del patron State para el ciclo de vida de un reclamo.
 *
 * Cada estado concreto conoce como avanzar el reclamo y como identificarse,
 * evitando que Reclamo concentre todas las reglas de transicion.
 */
public interface IEstadoReclamo {
    void avanzar(Reclamo reclamo);

    String getNombre();
}
