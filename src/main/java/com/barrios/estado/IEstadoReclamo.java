package com.barrios.estado;

import com.barrios.modelo.Reclamo;

///patrón State, cada estado conoce como avanzar el reclamo y como identificarse./**/**/**/**

public interface IEstadoReclamo {
    void avanzar(Reclamo reclamo);

    String getNombre();
}
