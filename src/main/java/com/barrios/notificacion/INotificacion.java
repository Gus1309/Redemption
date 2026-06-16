package com.barrios.notificacion;

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
