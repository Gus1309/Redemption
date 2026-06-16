package com.barrios.notificacion;

/**
 * Notificacion generada ante la creacion o cancelacion de una ReservaAmenidad.
 */
public class NotificacionReserva implements INotificacion {

    private final String detalle;

    public NotificacionReserva(String detalle) {
        this.detalle = detalle;
    }

    @Override
    public String generarMensaje() {
        return "[Reserva] " + detalle;
    }

    @Override
    public String tipo() {
        return "RESERVA";
    }
}