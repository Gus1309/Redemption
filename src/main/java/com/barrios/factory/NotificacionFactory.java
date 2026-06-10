package com.barrios.factory;

import com.barrios.notificacion.INotificacion;
import com.barrios.notificacion.NotificacionExpensa;
import com.barrios.notificacion.NotificacionIncidente;
import com.barrios.notificacion.NotificacionReclamo;
import com.barrios.notificacion.NotificacionReserva;
import com.barrios.notificacion.NotificacionVisita;

public class NotificacionFactory {

    /**
     * Crea la notificacion segun el tipo.
     *
     * @param tipo    "reclamo", "incidente", "visita", "reserva" o "expensa" (case-insensitive)
     * @param detalle mensaje descriptivo en la notificacion
     */
    public INotificacion crear(String tipo, String detalle) {
        if (tipo == null) {
            throw new IllegalArgumentException("El tipo de notificacion no puede ser nulo.");
        }
        return switch (tipo.toLowerCase()) {
            case "reclamo"   -> new NotificacionReclamo(detalle);
            case "incidente" -> new NotificacionIncidente(detalle);
            case "visita"    -> new NotificacionVisita(detalle);
            case "reserva"   -> new NotificacionReserva(detalle);
            case "expensa"   -> new NotificacionExpensa(detalle);
            default -> throw new IllegalArgumentException("Tipo de notificacion desconocido: " + tipo);
        };
    }
}