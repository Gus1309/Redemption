package com.barrios.notificacion;

public interface IObservable {
    default void agregarObservador(IObserver observador) {
        agregarObserver(observador);
    }

    default void removerObservador(IObserver observador) {
        removerObserver(observador);
    }

    default void notificarObservadores(String mensaje) {
        notificarObserver(mensaje);
    }

    default void agregarObserver(IObserver observador) {
    }

    default void removerObserver(IObserver observador) {
    }

    default void notificarObserver(String mensaje) {
    }
}
