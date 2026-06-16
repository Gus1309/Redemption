package com.barrios.servicio;

public class ResumenBarrio {
    private final String nombreBarrio;
    private final int reclamos;
    private final int reservas;
    private final int accesos;
    private final int incidentes;
    private final int expensas;

    public ResumenBarrio(String nombreBarrio, int reclamos, int reservas, int accesos, int incidentes, int expensas) {
        this.nombreBarrio = nombreBarrio;
        this.reclamos = reclamos;
        this.reservas = reservas;
        this.accesos = accesos;
        this.incidentes = incidentes;
        this.expensas = expensas;
    }

    public String getNombreBarrio() {
        return nombreBarrio;
    }

    public int getReclamos() {
        return reclamos;
    }

    public int getReservas() {
        return reservas;
    }

    public int getAccesos() {
        return accesos;
    }

    public int getIncidentes() {
        return incidentes;
    }

    public int getExpensas() {
        return expensas;
    }
}
