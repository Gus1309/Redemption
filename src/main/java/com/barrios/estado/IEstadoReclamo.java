package com.barrios.estado;

import com.barrios.modelo.Reclamo;

public interface IEstadoReclamo {
    void avanzar(Reclamo reclamo);

    String getNombre();
}
