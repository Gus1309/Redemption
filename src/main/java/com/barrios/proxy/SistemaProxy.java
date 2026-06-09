package com.barrios.proxy;

import com.barrios.modelo.AutorizacionVisita;
import com.barrios.modelo.Incidente;
import com.barrios.modelo.Reclamo;
import com.barrios.modelo.ReservaAmenidad;
import com.barrios.servicio.ISistema;

public class SistemaProxy implements ISistema {
    private ISistema sistemaReal;

    public SistemaProxy(ISistema sistemaReal) {
        this.sistemaReal = sistemaReal;
    }

    @Override
    public void registrarReclamo(Reclamo reclamo) {
        // TODO: validar permisos antes de delegar.
        sistemaReal.registrarReclamo(reclamo);
    }

    @Override
    public void registrarIncidente(Incidente incidente) {
        // TODO: validar permisos antes de delegar.
        sistemaReal.registrarIncidente(incidente);
    }

    @Override
    public void autorizarVisita(AutorizacionVisita autorizacionVisita) {
        // TODO: validar permisos antes de delegar.
        sistemaReal.autorizarVisita(autorizacionVisita);
    }

    @Override
    public void registrarReserva(ReservaAmenidad reservaAmenidad) {
        // TODO: validar permisos antes de delegar.
        sistemaReal.registrarReserva(reservaAmenidad);
    }

    public ISistema getSistemaReal() {
        return sistemaReal;
    }

    public void setSistemaReal(ISistema sistemaReal) {
        this.sistemaReal = sistemaReal;
    }
}
