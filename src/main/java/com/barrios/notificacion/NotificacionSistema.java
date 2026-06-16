package com.barrios.notificacion;

public class NotificacionSistema implements INotificacion {
    private final String tipo;

    public NotificacionSistema(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String formatear(String mensaje) {
        return "[" + tipo + "] " + mensaje;
    }
}
