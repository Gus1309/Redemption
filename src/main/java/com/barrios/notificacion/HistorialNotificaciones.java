package com.barrios.notificacion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistorialNotificaciones implements IObserver {
    private final List<String> mensajes = new ArrayList<>();

    @Override
    public void actualizar(String mensaje) {
        mensajes.add(mensaje);
    }

    public List<String> getMensajes() {
        return Collections.unmodifiableList(mensajes);
    }
}
