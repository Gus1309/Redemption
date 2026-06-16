package com.barrios.servicio;

import com.barrios.modelo.Barrio;
import com.barrios.modelo.Reclamo;
import com.barrios.notificacion.CentroNotificaciones;

public class GestionReclamos {
    private final CentroNotificaciones centroNotificaciones;

    public GestionReclamos(CentroNotificaciones centroNotificaciones) {
        this.centroNotificaciones = centroNotificaciones;
    }

    public ResultadoOperacion<Reclamo> registrarReclamo(Barrio barrio, Reclamo reclamo) {
        barrio.agregarReclamo(reclamo);
        return ResultadoOperacion.ok("[OK] Reclamo creado en estado " + reclamo.getEstado().getNombre(), reclamo);
    }

    public ResultadoOperacion<Reclamo> avanzarReclamo(Reclamo reclamo) {
        reclamo.avanzarEstado();
        centroNotificaciones.notificar("RECLAMO", "Reclamo #" + reclamo.getId()
                + " actualizado a " + reclamo.getEstado().getNombre());
        return ResultadoOperacion.ok("[OK] Reclamo actualizado a " + reclamo.getEstado().getNombre(), reclamo);
    }
}
