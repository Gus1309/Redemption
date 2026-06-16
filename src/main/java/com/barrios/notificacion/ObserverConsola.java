package com.barrios.notificacion;

/**
 * Implementa un oserver que imprime por consola.
 */
public class ObserverConsola implements IObserver {

    @Override
    public void actualizar(String evento, Object origen) {
        System.out.println("[ObserverConsola] Evento: " + evento + " | Origen: " + origen);
    }
}