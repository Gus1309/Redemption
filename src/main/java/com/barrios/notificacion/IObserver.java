package com.barrios.notificacion;

public interface IObserver {
    default void actualizar(String mensaje) {
    }

    default void actualizar(String evento, Object origen) {
        actualizar(evento + " | " + origen);
    }
}
