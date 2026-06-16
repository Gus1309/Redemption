package com.barrios.servicio;

import com.barrios.modelo.Barrio;
import com.barrios.modelo.Incidente;
import com.barrios.notificacion.CentroNotificaciones;

public class GestionIncidentes {
    private final CentroNotificaciones centroNotificaciones;

    public GestionIncidentes(CentroNotificaciones centroNotificaciones) {
        this.centroNotificaciones = centroNotificaciones;
    }

    public ResultadoOperacion<Incidente> registrarIncidente(Barrio barrio, Incidente incidente) {
        barrio.agregarIncidente(incidente);
        centroNotificaciones.notificar("INCIDENTE", "Incidente #" + incidente.getId()
                + " registrado en " + barrio.getNombre());
        return ResultadoOperacion.ok("[OK] Incidente registrado en " + barrio.getNombre(), incidente);
    }

    public ResultadoOperacion<Incidente> actualizarIncidente(Incidente incidente, String nuevoEstado) {
        incidente.cambiarEstado(nuevoEstado);
        centroNotificaciones.notificar("INCIDENTE", "Incidente #" + incidente.getId()
                + " actualizado a " + nuevoEstado);
        return ResultadoOperacion.ok("[OK] Incidente actualizado a " + nuevoEstado, incidente);
    }
}
