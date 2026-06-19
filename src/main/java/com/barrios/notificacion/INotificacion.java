package com.barrios.notificacion;

/**
 * Abstraccion comun para las notificaciones del sistema.
 *
 * Permite tratar distintos tipos de notificacion de manera uniforme y facilita
 * agregar nuevas variantes sin modificar a quienes las consumen.
 */
public interface INotificacion {
    default String formatear(String mensaje) {
        return "[" + tipo() + "] " + mensaje;
    }

    default String generarMensaje() {
        return "";
    }

    default String tipo() {
        return "SISTEMA";
    }
}
