package com.barrios.notificacion;

/**
 * Notificacion generada ante la emision o vencimiento de una Expensa.
 */
public class NotificacionExpensa implements INotificacion {

    private final String detalle;

    public NotificacionExpensa(String detalle) {
        this.detalle = detalle;
    }

    @Override
    public String generarMensaje() {
        return "[Expensa] " + detalle;
    }

    @Override
    public String tipo() {
        return "EXPENSA";
    }
}