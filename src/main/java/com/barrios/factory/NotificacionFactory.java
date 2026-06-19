package com.barrios.factory;

import com.barrios.notificacion.INotificacion;
import com.barrios.notificacion.NotificacionSistema;

/**
 * Patron Factory.
 *
 * Centraliza la creacion de notificaciones y evita que las clases de negocio
 * instancien implementaciones concretas de forma dispersa. Se utiliza para
 * eventos del sistema como reclamos, incidentes, visitas, reservas o expensas.
 */
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
