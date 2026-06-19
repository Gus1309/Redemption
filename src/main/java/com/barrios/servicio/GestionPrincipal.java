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
import com.barrios.notificacion.CentroNotificaciones;

import java.util.List;

/**
 * Patrones Singleton y Facade.
 *
 * Como Singleton mantiene una unica instancia central del sistema durante la
 * ejecucion. Como Facade ofrece una interfaz simplificada para operar sobre
 * barrios, visitas, accesos, reservas, reclamos, incidentes, novedades y
 * expensas, delegando cada responsabilidad en servicios especificos.
 */
public class GestionPrincipal implements ISistema {
    private static GestionPrincipal instancia;
    private final CentroNotificaciones centroNotificaciones;
    private final GestionBarrios gestionBarrios;
    private final GestionVisitas gestionVisitas;
    private final RegistroAccesos registroAccesos;
    private final GestionReservas gestionReservas;
    private final GestionReclamos gestionReclamos;
    private final GestionIncidentes gestionIncidentes;
    private final GestionNovedades gestionNovedades;
    private final GestionExpensas gestionExpensas;

    private GestionPrincipal() {
        this.centroNotificaciones = new CentroNotificaciones();
        this.gestionBarrios = new GestionBarrios();
        this.gestionVisitas = new GestionVisitas();
        this.registroAccesos = new RegistroAccesos();
        this.gestionReservas = new GestionReservas();
        this.gestionReclamos = new GestionReclamos(centroNotificaciones);
        this.gestionIncidentes = new GestionIncidentes(centroNotificaciones);
        this.gestionNovedades = new GestionNovedades(centroNotificaciones);
        this.gestionExpensas = new GestionExpensas();
    }

    public static GestionPrincipal getInstancia() {
        if (instancia == null) {
            instancia = new GestionPrincipal();
        }
        return instancia;
    }

    @Override
    public ResultadoOperacion<Barrio> crearBarrio(Usuario usuario, Barrio barrio) {
        return gestionBarrios.crearBarrio(barrio);
    }

    @Override
    public ResultadoOperacion<Vivienda> registrarVivienda(Usuario usuario, Barrio barrio, Vivienda vivienda) {
        return gestionBarrios.registrarVivienda(barrio, vivienda);
    }

    @Override
    public ResultadoOperacion<Amenidad> registrarAmenidad(Usuario usuario, Barrio barrio, Amenidad amenidad) {
        return gestionBarrios.registrarAmenidad(barrio, amenidad);
    }

    @Override
    public ResultadoOperacion<AutorizacionVisita> autorizarVisita(Usuario usuario, Barrio barrio, AutorizacionVisita autorizacionVisita) {
        return gestionVisitas.autorizarVisita(barrio, autorizacionVisita);
    }

    @Override
    public ResultadoOperacion<?> registrarIngreso(Usuario usuario, Barrio barrio, AutorizacionVisita autorizacionVisita) {
        return registroAccesos.registrarIngreso(barrio, autorizacionVisita);
    }

    @Override
    public ResultadoOperacion<?> registrarEgreso(Usuario usuario, Barrio barrio, Visitante visitante) {
        return registroAccesos.registrarEgreso(barrio, visitante);
    }

    @Override
    public ResultadoOperacion<ReservaAmenidad> registrarReserva(Usuario usuario, Barrio barrio, ReservaAmenidad reservaAmenidad) {
        return gestionReservas.registrarReserva(barrio, reservaAmenidad);
    }

    @Override
    public ResultadoOperacion<Reclamo> registrarReclamo(Usuario usuario, Barrio barrio, Reclamo reclamo) {
        return gestionReclamos.registrarReclamo(barrio, reclamo);
    }

    @Override
    public ResultadoOperacion<Reclamo> avanzarReclamo(Usuario usuario, Reclamo reclamo) {
        return gestionReclamos.avanzarReclamo(reclamo);
    }

    @Override
    public ResultadoOperacion<Incidente> registrarIncidente(Usuario usuario, Barrio barrio, Incidente incidente) {
        return gestionIncidentes.registrarIncidente(barrio, incidente);
    }

    @Override
    public ResultadoOperacion<Incidente> actualizarIncidente(Usuario usuario, Incidente incidente, String nuevoEstado) {
        return gestionIncidentes.actualizarIncidente(incidente, nuevoEstado);
    }

    @Override
    public ResultadoOperacion<Novedad> publicarNovedad(Usuario usuario, Barrio barrio, Novedad novedad) {
        return gestionNovedades.publicarNovedad(barrio, novedad);
    }

    @Override
    public ResultadoOperacion<Expensa> registrarExpensa(Usuario usuario, Barrio barrio, Expensa expensa) {
        return gestionExpensas.registrarExpensa(barrio, expensa);
    }

    @Override
    public ResultadoOperacion<ResumenBarrio> obtenerResumenBarrio(Usuario usuario, Barrio barrio) {
        ResumenBarrio resumen = new ResumenBarrio(
                barrio.getNombre(),
                barrio.getReclamos().size(),
                barrio.getReservas().size(),
                barrio.getAccesos().size(),
                barrio.getIncidentes().size(),
                barrio.getExpensas().size()
        );
        return ResultadoOperacion.ok("[OK] Resumen generado para " + barrio.getNombre(), resumen);
    }

    @Override
    public List<Barrio> listarBarrios(Usuario usuario) {
        return gestionBarrios.listarBarrios();
    }

    public CentroNotificaciones getCentroNotificaciones() {
        return centroNotificaciones;
    }
}
