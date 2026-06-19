package com.barrios.notificacion;

/**
 * Contrato del patron Observer para objetos observables.
 *
 * Define operaciones para registrar, eliminar y notificar observadores, de
 * modo que los eventos se comuniquen sin depender de implementaciones
 * concretas.
 */
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
