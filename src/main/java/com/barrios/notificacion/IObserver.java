package com.barrios.notificacion;

/**
 * Contrato del patron Observer para objetos que reciben notificaciones.
 *
 * Permite que el observable trabaje contra una abstraccion y no dependa de
 * clases concretas, favoreciendo bajo acoplamiento.
 */
public interface IObserver {
    default void actualizar(String mensaje) {
    }

    default void actualizar(String evento, Object origen) {
        actualizar(evento + " | " + origen);
    }
}
