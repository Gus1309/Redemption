package com.barrios.estado;

import com.barrios.modelo.Reclamo;

public class EstadoEnProceso implements IEstadoReclamo {
    @Override
    public void avanzar(Reclamo reclamo) {
        // TODO: avanzar a EstadoResuelto.
    }

    @Override
    public String getNombre() {
        return "En proceso";
    }
}
