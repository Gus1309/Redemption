package com.barrios.notificacion;

public interface IObserver {
    void actualizar(String evento, Object origen);
}