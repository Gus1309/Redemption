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
    private final PersonalSeguridad seguridad;
    private final Tecnico tecnico;

    public DatosDemoService() {
        this.gestionPrincipal = GestionPrincipal.getInstancia();
        this.sistema = new SistemaProxy(gestionPrincipal);
        this.administrador = new Administrador(1L, "Ana Administradora", "ana@barrios.com", "Administracion");
        this.propietario = new Propietario(2L, "Pedro Propietario", "pedro@barrios.com", "1133445566");
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

        Vivienda vivienda = new Vivienda(1L, "Casa 15", propietario);
        Amenidad quincho = new Amenidad(1L, "Quincho", "Salon con parrilla");
        sistema.registrarVivienda(administrador, losRobles, vivienda);
        sistema.registrarAmenidad(administrador, losRobles, quincho);

        Visitante visitante = new Visitante(1L, "Juan Perez", "30111222");
        AutorizacionVisita autorizacion = new AutorizacionVisita(1L, visitante, propietario, LocalDate.now(), "PENDIENTE");
        sistema.autorizarVisita(propietario, losRobles, autorizacion);
        sistema.registrarIngreso(seguridad, losRobles, autorizacion);
        sistema.registrarEgreso(seguridad, losRobles, visitante);

        ReservaAmenidad reserva = new ReservaAmenidad(1L, quincho, propietario, LocalDate.now().plusDays(2), "PENDIENTE");
        sistema.registrarReserva(propietario, losRobles, reserva);

        Reclamo reclamo = new Reclamo(1L, "Luz quemada en acceso principal", LocalDate.now(), new EstadoPendiente());
        sistema.registrarReclamo(propietario, losRobles, reclamo);
        sistema.avanzarReclamo(tecnico, reclamo);
        sistema.avanzarReclamo(tecnico, reclamo);

        Incidente incidente = new Incidente(1L, "Porton de ingreso trabado", LocalDate.now(), "ABIERTO");
        sistema.registrarIncidente(tecnico, losRobles, incidente);
        sistema.actualizarIncidente(tecnico, incidente, "EN_REVISION");

        Novedad novedad = new Novedad(1L, "Corte de agua programado para el viernes", LocalDate.now());
        sistema.publicarNovedad(administrador, losRobles, novedad);

        Expensa expensa = new Expensa(1L, "2026-06", new BigDecimal("125000.00"), "PENDIENTE");
        sistema.registrarExpensa(administrador, losRobles, expensa);
    }

    public List<Barrio> listarBarrios() {
        return gestionPrincipal.listarBarrios(administrador);
    }

    public Optional<Barrio> buscarBarrio(Long id) {
        return listarBarrios().stream()
                .filter(barrio -> barrio.getId().equals(id))
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
}
