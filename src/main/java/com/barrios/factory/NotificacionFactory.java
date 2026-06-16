package com.barrios.factory;

import com.barrios.notificacion.INotificacion;
import com.barrios.notificacion.NotificacionSistema;

public class NotificacionFactory {
    public INotificacion crearNotificacion(String tipo) {
        return switch (tipo) {
            case "RECLAMO" -> new NotificacionSistema("NOTIFICACION_RECLAMO");
            case "INCIDENTE" -> new NotificacionSistema("NOTIFICACION_INCIDENTE");
            case "NOVEDAD" -> new NotificacionSistema("NOTIFICACION_NOVEDAD");
            default -> new NotificacionSistema("NOTIFICACION");
        };
    }
}
