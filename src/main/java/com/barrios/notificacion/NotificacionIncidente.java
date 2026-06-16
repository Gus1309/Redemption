package com.barrios.notificacion;

/**
 * Notificacion generada ante el registro o actualizacion de un Incidente.
 */
public class NotificacionIncidente implements INotificacion {

    private final String detalle;

    public NotificacionIncidente(String detalle) {
        this.detalle = detalle;
    }

    @Override
    public String generarMensaje() {
        return "[Incidente] " + detalle;
    }

    @Override
    public String tipo() {
        return "INCIDENTE";
    }
}