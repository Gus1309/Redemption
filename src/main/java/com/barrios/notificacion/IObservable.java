package com.barrios.notificacion;

public interface IObservable {
    void agregarObservador(IObserver observador);

    void removerObservador(IObserver observador);

    void notificarObservadores(String mensaje);
}
