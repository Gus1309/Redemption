package com.barrios.servicio;

import com.barrios.modelo.AutorizacionVisita;
import com.barrios.modelo.Incidente;
import com.barrios.modelo.Reclamo;
import com.barrios.modelo.ReservaAmenidad;

public class GestionPrincipal implements ISistema {
    private static GestionPrincipal instancia;

    private GestionPrincipal() {
    }

    public static GestionPrincipal getInstancia() {
        if (instancia == null) {
            instancia = new GestionPrincipal();
        }
        return instancia;
    }

    @Override
    public void registrarReclamo(Reclamo reclamo) {
        // TODO: delegar en el servicio correspondiente.
    }

    @Override
    public void registrarIncidente(Incidente incidente) {
        // TODO: delegar en el servicio correspondiente.
    }

    @Override
    public void autorizarVisita(AutorizacionVisita autorizacionVisita) {
        // TODO: delegar en el servicio correspondiente.
    }

    @Override
    public void registrarReserva(ReservaAmenidad reservaAmenidad) {
        // TODO: delegar en el servicio correspondiente.
    }
}
