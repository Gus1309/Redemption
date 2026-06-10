package com.barrios.notificacion;

/**
 * Notificacion generada ante la autorizacion o rechazo de una visita.
 */
public class NotificacionVisita implements INotificacion {

    private final String detalle;

    public NotificacionVisita(String detalle) {
        this.detalle = detalle;
    }

    @Override
    public String generarMensaje() {
        return "[Visita] " + detalle;
    }

    @Override
    public String tipo() {
        return "VISITA";
    }
}