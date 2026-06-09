package com.barrios.servicio;

import com.barrios.modelo.AutorizacionVisita;
import com.barrios.modelo.Incidente;
import com.barrios.modelo.Reclamo;
import com.barrios.modelo.ReservaAmenidad;

public interface ISistema {
    void registrarReclamo(Reclamo reclamo);

    void registrarIncidente(Incidente incidente);

    void autorizarVisita(AutorizacionVisita autorizacionVisita);

    void registrarReserva(ReservaAmenidad reservaAmenidad);
}
