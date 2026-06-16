package com.barrios.servicio;

import com.barrios.modelo.Barrio;
import com.barrios.modelo.Novedad;
import com.barrios.notificacion.CentroNotificaciones;

public class GestionNovedades {
    private final CentroNotificaciones centroNotificaciones;

    public GestionNovedades(CentroNotificaciones centroNotificaciones) {
        this.centroNotificaciones = centroNotificaciones;
    }

    public ResultadoOperacion<Novedad> publicarNovedad(Barrio barrio, Novedad novedad) {
        barrio.agregarNovedad(novedad);
        centroNotificaciones.notificar("NOVEDAD", "Nueva novedad en " + barrio.getNombre()
                + ": " + novedad.getDescripcion());
        return ResultadoOperacion.ok("[OK] Novedad publicada en " + barrio.getNombre(), novedad);
    }
}
