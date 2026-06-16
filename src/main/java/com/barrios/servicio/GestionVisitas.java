package com.barrios.servicio;

import com.barrios.modelo.AutorizacionVisita;
import com.barrios.modelo.Barrio;

public class GestionVisitas {
    public ResultadoOperacion<AutorizacionVisita> autorizarVisita(Barrio barrio, AutorizacionVisita autorizacion) {
        autorizacion.setEstado("AUTORIZADA");
        barrio.agregarVisitante(autorizacion.getVisitante());
        barrio.agregarAutorizacion(autorizacion);
        String mensaje = "[OK] Visita autorizada para " + autorizacion.getVisitante().getNombre()
                + " en " + barrio.getNombre();
        return ResultadoOperacion.ok(mensaje, autorizacion);
    }
}
