package com.barrios.notificacion;


public class NotificacionReclamo implements INotificacion {

    private final String detalle;

    public NotificacionReclamo(String detalle) {
        this.detalle = detalle;
    }

    @Override
    public String generarMensaje() {
        return "[Reclamo] " + detalle;
    }

    @Override
    public String tipo() {
        return "RECLAMO";
    }
}