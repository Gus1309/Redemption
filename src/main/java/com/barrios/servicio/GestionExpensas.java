package com.barrios.servicio;

import com.barrios.modelo.Barrio;
import com.barrios.modelo.Expensa;

public class GestionExpensas {
    public ResultadoOperacion<Expensa> registrarExpensa(Barrio barrio, Expensa expensa) {
        barrio.agregarExpensa(expensa);
        return ResultadoOperacion.ok("[OK] Expensa registrada en " + barrio.getNombre(), expensa);
    }
}
