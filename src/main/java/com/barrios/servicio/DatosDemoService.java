package com.barrios.servicio;

import com.barrios.estado.EstadoPendiente;
import com.barrios.modelo.Administrador;
import com.barrios.modelo.Amenidad;
import com.barrios.modelo.AutorizacionVisita;
import com.barrios.modelo.Barrio;
import com.barrios.modelo.Expensa;
import com.barrios.modelo.Incidente;
import com.barrios.modelo.Novedad;
import com.barrios.modelo.PersonalSeguridad;
import com.barrios.modelo.Propietario;
import com.barrios.modelo.Reclamo;
import com.barrios.modelo.ReservaAmenidad;
import com.barrios.modelo.Tecnico;
import com.barrios.modelo.Visitante;
import com.barrios.modelo.Vivienda;
import com.barrios.proxy.SistemaProxy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DatosDemoService {
    private final GestionPrincipal gestionPrincipal;
    private final ISistema sistema;
    private final Administrador administrador;
    private final Propietario propietario;
    private final Propietario propietarioDos;
    private final Propietario propietarioTres;
    private final Propietario propietarioCuatro;
    private final Propietario propietarioCinco;
    private final PersonalSeguridad seguridad;
    private final Tecnico tecnico;

    public DatosDemoService() {
        this.gestionPrincipal = GestionPrincipal.getInstancia();
        this.sistema = new SistemaProxy(gestionPrincipal);
        this.administrador = new Administrador(1L, "Ana Administradora", "ana@barrios.com", "Administracion");
        this.propietario = new Propietario(2L, "Pedro Propietario", "pedro@barrios.com", "1133445566");
        this.propietarioDos = new Propietario(5L, "Lucia Lorenzo", "lucia@barrios.com", "1144556677");
        this.propietarioTres = new Propietario(6L, "Marcos Diaz", "marcos@barrios.com", "1155667788");
        this.propietarioCuatro = new Propietario(7L, "Carla Nunez", "carla@barrios.com", "1166778899");
        this.propietarioCinco = new Propietario(8L, "Diego Fernandez", "diego@barrios.com", "1177889900");
        this.seguridad = new PersonalSeguridad(3L, "Sofia Seguridad", "sofia@barrios.com", "Manana");
        this.tecnico = new Tecnico(4L, "Tomas Tecnico", "tomas@barrios.com", "Mantenimiento");
        inicializar();
    }

    private void inicializar() {
        if (!gestionPrincipal.listarBarrios(administrador).isEmpty()) {
            return;
        }

        Barrio losRobles = new Barrio(1L, "Los Robles", "Barrio familiar con amenities y control de accesos");
        Barrio laEscondida = new Barrio(2L, "La Escondida", "Barrio residencial con datos separados");
        sistema.crearBarrio(administrador, losRobles);
        sistema.crearBarrio(administrador, laEscondida);

        Vivienda vivienda = new Vivienda(1L, "15", null, propietario, losRobles);
        Vivienda viviendaDos = new Vivienda(2L, "98", null, propietarioDos, losRobles);
        Vivienda viviendaTres = new Vivienda(3L, "23", null, propietarioTres, losRobles);
        Vivienda viviendaCuatro = new Vivienda(4L, "47", null, propietarioCuatro, losRobles);
        Vivienda viviendaCinco = new Vivienda(5L, "61", null, propietarioCinco, losRobles);

        Amenidad quincho = new Amenidad(1L, "Quincho", "Salon con parrilla");
        Amenidad pileta = new Amenidad(2L, "Pileta", "Piscina climatizada");
        Amenidad sum = new Amenidad(3L, "SUM", "Salon de usos multiples");
        Amenidad cancha = new Amenidad(4L, "Cancha de futbol", "Cancha de cesped sintetico");

        sistema.registrarVivienda(administrador, losRobles, vivienda);
        sistema.registrarVivienda(administrador, losRobles, viviendaDos);
        sistema.registrarVivienda(administrador, losRobles, viviendaTres);
        sistema.registrarVivienda(administrador, losRobles, viviendaCuatro);
        sistema.registrarVivienda(administrador, losRobles, viviendaCinco);

        sistema.registrarAmenidad(administrador, losRobles, quincho);
        sistema.registrarAmenidad(administrador, losRobles, pileta);
        sistema.registrarAmenidad(administrador, losRobles, sum);
        sistema.registrarAmenidad(administrador, losRobles, cancha);

        Visitante visitante = new Visitante(1L, "Juan Perez", "30111222");
        AutorizacionVisita autorizacion = new AutorizacionVisita(1L, visitante, propietario, LocalDate.now(), "PENDIENTE");
        sistema.autorizarVisita(propietario, losRobles, autorizacion);
        sistema.registrarIngreso(seguridad, losRobles, autorizacion);
        sistema.registrarEgreso(seguridad, losRobles, visitante);

        ReservaAmenidad reserva = new ReservaAmenidad(1L, quincho, propietario, LocalDate.now().plusDays(2), "PENDIENTE");
        sistema.registrarReserva(propietario, losRobles, reserva);

        Reclamo reclamo = new Reclamo(1L, "Luz quemada en acceso principal", LocalDate.now(), new EstadoPendiente());
        sistema.registrarReclamo(propietario, losRobles, reclamo);
        sistema.avanzarReclamo(seguridad, reclamo);
        sistema.avanzarReclamo(seguridad, reclamo);

        Incidente incidente = new Incidente(1L, "Porton de ingreso trabado", LocalDate.now(), "ABIERTO");
        sistema.registrarIncidente(seguridad, losRobles, incidente);
        sistema.actualizarIncidente(tecnico, incidente, "EN_REVISION");

        Novedad novedad = new Novedad(1L, "Corte de agua programado para el viernes", LocalDate.now());
        sistema.publicarNovedad(administrador, losRobles, novedad);

        Expensa expensa = new Expensa(1L, "2026-06", new BigDecimal("125000.00"), "PENDIENTE");
        Expensa expensaEscondida = new Expensa(2L, "2026-06", new BigDecimal("98000.00"), "PAGA");
        sistema.registrarExpensa(administrador, losRobles, expensa);
        sistema.registrarExpensa(administrador, laEscondida, expensaEscondida);
    }

    public List<Barrio> listarBarrios() {
        return gestionPrincipal.listarBarrios(administrador);
    }

    public Optional<Barrio> buscarBarrio(Long id) {
        return listarBarrios().stream()
                .filter(barrio -> barrio.getId().equals(id))
                .findFirst();
    }

    public Optional<Propietario> buscarPropietarioPorLote(Barrio barrio, String lote) {
        if (lote == null || lote.isBlank()) {
            return Optional.empty();
        }
        String loteNormalizado = lote.trim();
        return barrio.getViviendas().stream()
                .filter(v -> v.getNumero() != null && v.getNumero().trim().equalsIgnoreCase(loteNormalizado))
                .map(Vivienda::getPropietario)
                .findFirst();
    }

    public List<DashboardBarrio> obtenerDashboard() {
        return listarBarrios().stream()
                .map(barrio -> new DashboardBarrio(barrio, obtenerResumen(barrio)))
                .toList();
    }

    public ResumenBarrio obtenerResumen(Barrio barrio) {
        return gestionPrincipal.obtenerResumenBarrio(administrador, barrio).getDato();
    }

    public ResultadoOperacion<Reclamo> crearReclamo(Barrio barrio, Reclamo reclamo) {
        return sistema.registrarReclamo(propietario, barrio, reclamo);
    }

    public ResultadoOperacion<Reclamo> avanzarReclamo(Barrio barrio, Long reclamoId) {
        Optional<Reclamo> reclamo = barrio.getReclamos().stream()
                .filter(item -> item.getId().equals(reclamoId))
                .findFirst();

        if (reclamo.isEmpty()) {
            return ResultadoOperacion.error("[ERROR] No se encontro el reclamo seleccionado");
        }

        return sistema.avanzarReclamo(seguridad, reclamo.get());
    }

    public ResultadoOperacion<Incidente> crearIncidente(Barrio barrio, Incidente incidente) {
        return sistema.registrarIncidente(seguridad, barrio, incidente);
    }

    public ResultadoOperacion<Incidente> actualizarIncidente(Barrio barrio, Long incidenteId, String nuevoEstado) {
        Optional<Incidente> incidente = barrio.getIncidentes().stream()
                .filter(item -> item.getId().equals(incidenteId))
                .findFirst();

        if (incidente.isEmpty()) {
            return ResultadoOperacion.error("[ERROR] No se encontro el incidente seleccionado");
        }

        return sistema.actualizarIncidente(tecnico, incidente.get(), nuevoEstado);
    }

    public ResultadoOperacion<AutorizacionVisita> crearAutorizacionVisita(Barrio barrio,
                                                                          String nombreVisitante,
                                                                          String documento,
                                                                          LocalDate fechaDesde,
                                                                          LocalDate fechaHasta) {
        long proximoIdVisitante = barrio.getAutorizaciones().size() + 1L;
        Visitante visitante = new Visitante(proximoIdVisitante, nombreVisitante, documento);
        AutorizacionVisita autorizacion = new AutorizacionVisita(
                proximoIdVisitante, visitante, propietario, fechaDesde, fechaHasta, "PENDIENTE");

        return sistema.autorizarVisita(propietario, barrio, autorizacion);
    }

    public ResultadoOperacion<?> registrarIngreso(Barrio barrio, Long autorizacionId) {
        Optional<AutorizacionVisita> autorizacion = barrio.getAutorizaciones().stream()
                .filter(item -> item.getId().equals(autorizacionId))
                .findFirst();

        if (autorizacion.isEmpty()) {
            return ResultadoOperacion.error("[ERROR] No se encontro la autorizacion seleccionada");
        }

        return sistema.registrarIngreso(seguridad, barrio, autorizacion.get());
    }

    public ResultadoOperacion<?> registrarEgreso(Barrio barrio, Long autorizacionId) {
        Optional<AutorizacionVisita> autorizacion = barrio.getAutorizaciones().stream()
                .filter(item -> item.getId().equals(autorizacionId))
                .findFirst();

        if (autorizacion.isEmpty()) {
            return ResultadoOperacion.error("[ERROR] No se encontro la autorizacion seleccionada");
        }

        return sistema.registrarEgreso(seguridad, barrio, autorizacion.get().getVisitante());
    }

    public ResultadoOperacion<?> crearReserva(Barrio barrio, Long amenidadId, LocalDate fecha, String lote) {
        Optional<Amenidad> amenidad = barrio.getAmenidades().stream()
                .filter(item -> item.getId().equals(amenidadId))
                .findFirst();

        if (amenidad.isEmpty()) {
            return ResultadoOperacion.error("[ERROR] No se encontro la amenidad seleccionada");
        }

        boolean fechaOcupada = barrio.getReservas().stream()
                .anyMatch(reserva -> reserva.getAmenidad().getId().equals(amenidadId)
                        && reserva.getFecha().equals(fecha));

        if (fechaOcupada) {
            return ResultadoOperacion.error("[ERROR] La amenidad ya tiene una reserva para esa fecha");
        }

        Propietario quienReserva = buscarPropietarioPorLote(barrio, lote).orElse(propietario);

        long proximoId = barrio.getReservas().stream()
                .mapToLong(ReservaAmenidad::getId)
                .max()
                .orElse(0L) + 1L;

        ReservaAmenidad reserva = new ReservaAmenidad(
                proximoId, amenidad.get(), quienReserva, fecha, "PENDIENTE");

        return sistema.registrarReserva(quienReserva, barrio, reserva);
    }
}