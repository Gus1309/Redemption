package com.barrios.estado;

import com.barrios.modelo.Reclamo;

public class EstadoResuelto implements IEstadoReclamo {

    @Override
    public void avanzar(Reclamo reclamo) {
        // Estado final: no hay transición posible.
        System.out.println("El reclamo ya se encuentra en estado Resuelto. No puede avanzar.");
    }

    @Override
    public String getNombre() {
        return "Resuelto";
    }
}