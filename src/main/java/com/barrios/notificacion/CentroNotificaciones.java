package com.barrios.notificacion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.barrios.factory.NotificacionFactory;

///Patron Observer.
///Actua como sujeto observable

public class CentroNotificaciones implements IObservable {
    private final List<IObserver> observadores = new ArrayList<>();
    private final NotificacionFactory factory = new NotificacionFactory();

    @Override
    public void agregarObservador(IObserver observador) {
        observadores.add(observador);
    }

    @Override
    public void removerObservador(IObserver observador) {
        observadores.remove(observador);
    }

    @Override
    public void notificarObservadores(String mensaje) {
        for (IObserver observador : observadores) {
            observador.actualizar(mensaje);
        }
    }

    public void notificar(String tipo, String mensaje) {
        INotificacion notificacion = factory.crearNotificacion(tipo);
        notificarObservadores(notificacion.formatear(mensaje));
    }

    public List<IObserver> getObservadores() {
        return Collections.unmodifiableList(observadores);
    }
}
