package com.barrios.servicio;

import com.barrios.modelo.Amenidad;
import com.barrios.modelo.AutorizacionVisita;
import com.barrios.modelo.Barrio;
import com.barrios.modelo.Expensa;
import com.barrios.modelo.Incidente;
import com.barrios.modelo.Novedad;
import com.barrios.modelo.Reclamo;
import com.barrios.modelo.ReservaAmenidad;
import com.barrios.modelo.Usuario;
import com.barrios.modelo.Visitante;
import com.barrios.modelo.Vivienda;

import java.util.List;

public interface ISistema {
    ResultadoOperacion<Barrio> crearBarrio(Usuario usuario, Barrio barrio);

    ResultadoOperacion<Vivienda> registrarVivienda(Usuario usuario, Barrio barrio, Vivienda vivienda);

    ResultadoOperacion<Amenidad> registrarAmenidad(Usuario usuario, Barrio barrio, Amenidad amenidad);

    ResultadoOperacion<AutorizacionVisita> autorizarVisita(Usuario usuario, Barrio barrio, AutorizacionVisita autorizacionVisita);

    ResultadoOperacion<?> registrarIngreso(Usuario usuario, Barrio barrio, AutorizacionVisita autorizacionVisita);

    ResultadoOperacion<?> registrarEgreso(Usuario usuario, Barrio barrio, Visitante visitante);

    ResultadoOperacion<ReservaAmenidad> registrarReserva(Usuario usuario, Barrio barrio, ReservaAmenidad reservaAmenidad);

    ResultadoOperacion<Reclamo> registrarReclamo(Usuario usuario, Barrio barrio, Reclamo reclamo);

    ResultadoOperacion<Reclamo> avanzarReclamo(Usuario usuario, Reclamo reclamo);

    ResultadoOperacion<Incidente> registrarIncidente(Usuario usuario, Barrio barrio, Incidente incidente);

    ResultadoOperacion<Incidente> actualizarIncidente(Usuario usuario, Incidente incidente, String nuevoEstado);

    ResultadoOperacion<Novedad> publicarNovedad(Usuario usuario, Barrio barrio, Novedad novedad);

    ResultadoOperacion<Expensa> registrarExpensa(Usuario usuario, Barrio barrio, Expensa expensa);

    ResultadoOperacion<ResumenBarrio> obtenerResumenBarrio(Usuario usuario, Barrio barrio);

    List<Barrio> listarBarrios(Usuario usuario);
}
