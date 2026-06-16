package com.barrios.servicio;

import com.barrios.modelo.AutorizacionVisita;
import com.barrios.modelo.Barrio;
import com.barrios.modelo.RegistroAcceso;
import com.barrios.modelo.Visitante;

import java.time.LocalDateTime;

public class RegistroAccesos {
    public ResultadoOperacion<RegistroAcceso> registrarIngreso(Barrio barrio, AutorizacionVisita autorizacion) {
        if (autorizacion == null || !"AUTORIZADA".equals(autorizacion.getEstado())) {
            return ResultadoOperacion.error("[ERROR] No existe una autorizacion valida para el ingreso");
        }

        RegistroAcceso acceso = new RegistroAcceso(null, autorizacion.getVisitante(), LocalDateTime.now(), "INGRESO");
        barrio.agregarAcceso(acceso);
        return ResultadoOperacion.ok("[OK] Ingreso registrado correctamente", acceso);
    }

    public ResultadoOperacion<RegistroAcceso> registrarEgreso(Barrio barrio, Visitante visitante) {
        RegistroAcceso acceso = new RegistroAcceso(null, visitante, LocalDateTime.now(), "EGRESO");
        barrio.agregarAcceso(acceso);
        return ResultadoOperacion.ok("[OK] Egreso registrado correctamente", acceso);
    }
}
