package com.barrios.servicio;

import com.barrios.modelo.Amenidad;
import com.barrios.modelo.Barrio;
import com.barrios.modelo.Vivienda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GestionBarrios {
    private final List<Barrio> barrios = new ArrayList<>();

    public ResultadoOperacion<Barrio> crearBarrio(Barrio barrio) {
        barrios.add(barrio);
        return ResultadoOperacion.ok("[OK] Barrio creado: " + barrio.getNombre(), barrio);
    }

    public ResultadoOperacion<Vivienda> registrarVivienda(Barrio barrio, Vivienda vivienda) {
        barrio.agregarVivienda(vivienda);
        return ResultadoOperacion.ok("[OK] Vivienda creada: " + vivienda.getDireccion(), vivienda);
    }

    public ResultadoOperacion<Amenidad> registrarAmenidad(Barrio barrio, Amenidad amenidad) {
        barrio.agregarAmenidad(amenidad);
        return ResultadoOperacion.ok("[OK] Amenidad creada: " + amenidad.getNombre(), amenidad);
    }

    public List<Barrio> listarBarrios() {
        return Collections.unmodifiableList(barrios);
    }
}
