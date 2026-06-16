package com.barrios.servicio;

import com.barrios.modelo.Barrio;
import com.barrios.modelo.ReservaAmenidad;

public class GestionReservas {
    public ResultadoOperacion<ReservaAmenidad> registrarReserva(Barrio barrio, ReservaAmenidad reserva) {
        reserva.setEstado("RESERVADA");
        barrio.agregarReserva(reserva);
        String mensaje = "[OK] Reserva creada para " + reserva.getAmenidad().getNombre()
                + " en " + barrio.getNombre();
        return ResultadoOperacion.ok(mensaje, reserva);
    }
}
