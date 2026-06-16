package com.barrios.servicio;

import com.barrios.modelo.Barrio;

public class DashboardBarrio {
    private final Barrio barrio;
    private final ResumenBarrio resumen;

    public DashboardBarrio(Barrio barrio, ResumenBarrio resumen) {
        this.barrio = barrio;
        this.resumen = resumen;
    }

    public Barrio getBarrio() {
        return barrio;
    }

    public ResumenBarrio getResumen() {
        return resumen;
    }
}
