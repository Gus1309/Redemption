package com.barrios.notificacion;

public interface IObservable {
    void agregarObserver(IObserver observador);

    void removerObserver(IObserver observador);

    void notificarObserver(String mensaje);
}
