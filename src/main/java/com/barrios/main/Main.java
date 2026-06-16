package com.barrios.main;

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
import com.barrios.notificacion.HistorialNotificaciones;
import com.barrios.proxy.SistemaProxy;
import com.barrios.servicio.GestionPrincipal;
import com.barrios.servicio.ISistema;
import com.barrios.servicio.ResultadoOperacion;
import com.barrios.servicio.ResumenBarrio;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE GESTION DE BARRIOS PRIVADOS ===");
        System.out.println();

        GestionPrincipal gestionPrincipal = GestionPrincipal.getInstancia();
        HistorialNotificaciones historial = new HistorialNotificaciones();
        gestionPrincipal.getCentroNotificaciones().agregarObservador(historial);
        ISistema sistema = new SistemaProxy(gestionPrincipal);

        Administrador administrador = new Administrador(1L, "Ana Administradora", "ana@barrios.com", "Administracion");
        Propietario propietario = new Propietario(2L, "Pedro Propietario", "pedro@barrios.com", "1133445566");
        PersonalSeguridad seguridad = new PersonalSeguridad(3L, "Sofia Seguridad", "sofia@barrios.com", "Noche");
        Tecnico tecnico = new Tecnico(4L, "Tomas Tecnico", "tomas@barrios.com", "Mantenimiento");

        Barrio losRobles = new Barrio(1L, "Los Robles", "Barrio familiar con amenities");
        Barrio laEscondida = new Barrio(2L, "La Escondida", "Barrio residencial");
        imprimir(sistema.crearBarrio(administrador, losRobles));
        imprimir(sistema.crearBarrio(administrador, laEscondida));

        System.out.println();
        System.out.println("=== VIVIENDAS Y AMENIDADES ===");
        Vivienda vivienda = new Vivienda(1L, "Casa 15", propietario);
        Amenidad quincho = new Amenidad(1L, "Quincho", "Salon con parrilla");
        imprimir(sistema.registrarVivienda(administrador, losRobles, vivienda));
        imprimir(sistema.registrarAmenidad(administrador, losRobles, quincho));

        System.out.println();
        System.out.println("=== VISITAS ===");
        Visitante visitante = new Visitante(1L, "Juan Perez", "30111222");
        AutorizacionVisita autorizacion = new AutorizacionVisita(1L, visitante, propietario, LocalDate.now(), "PENDIENTE");
        imprimir(sistema.autorizarVisita(propietario, losRobles, autorizacion));
        imprimir(sistema.registrarIngreso(seguridad, losRobles, autorizacion));
        imprimir(sistema.registrarEgreso(seguridad, losRobles, visitante));

        System.out.println();
        System.out.println("=== RESERVAS ===");
        ReservaAmenidad reserva = new ReservaAmenidad(1L, quincho, propietario, LocalDate.now().plusDays(2), "PENDIENTE");
        imprimir(sistema.registrarReserva(propietario, losRobles, reserva));

        System.out.println();
        System.out.println("=== RECLAMOS ===");
        Reclamo reclamo = new Reclamo(1L, "Luz quemada en acceso principal", LocalDate.now(), new EstadoPendiente());
        imprimir(sistema.registrarReclamo(propietario, losRobles, reclamo));
        imprimir(sistema.avanzarReclamo(tecnico, reclamo));
        imprimirUltimaNotificacion(historial);
        imprimir(sistema.avanzarReclamo(tecnico, reclamo));
        imprimirUltimaNotificacion(historial);

        System.out.println();
        System.out.println("=== INCIDENTES ===");
        Incidente incidente = new Incidente(1L, "Porton de ingreso trabado", LocalDate.now(), "ABIERTO");
        imprimir(sistema.registrarIncidente(tecnico, losRobles, incidente));
        imprimir(sistema.actualizarIncidente(tecnico, incidente, "EN_REVISION"));

        System.out.println();
        System.out.println("=== NOVEDADES ===");
        Novedad novedad = new Novedad(1L, "Corte de agua programado para el viernes", LocalDate.now());
        imprimir(sistema.publicarNovedad(administrador, losRobles, novedad));

        System.out.println();
        System.out.println("=== EXPENSAS ===");
        Expensa expensa = new Expensa(1L, "2026-06", new BigDecimal("125000.00"), "PENDIENTE");
        imprimir(sistema.registrarExpensa(administrador, losRobles, expensa));

        System.out.println();
        System.out.println("=== PERMISOS ===");
        Expensa expensaNoPermitida = new Expensa(2L, "2026-07", new BigDecimal("130000.00"), "PENDIENTE");
        imprimir(sistema.registrarExpensa(propietario, losRobles, expensaNoPermitida));

        System.out.println();
        System.out.println("=== NOTIFICACIONES ===");
        for (String mensaje : historial.getMensajes()) {
            System.out.println(mensaje);
        }

        System.out.println();
        System.out.println("=== RESUMEN POR BARRIO ===");
        imprimirResumen(sistema.obtenerResumenBarrio(administrador, losRobles).getDato());
        imprimirResumen(sistema.obtenerResumenBarrio(administrador, laEscondida).getDato());
    }

    private static void imprimir(ResultadoOperacion<?> resultado) {
        System.out.println(resultado.getMensaje());
    }

    private static void imprimirResumen(ResumenBarrio resumen) {
        System.out.println(resumen.getNombreBarrio() + ":");
        System.out.println("- Reclamos: " + resumen.getReclamos());
        System.out.println("- Reservas: " + resumen.getReservas());
        System.out.println("- Accesos: " + resumen.getAccesos());
        System.out.println("- Incidentes: " + resumen.getIncidentes());
        System.out.println("- Expensas: " + resumen.getExpensas());
        System.out.println();
    }

    private static void imprimirUltimaNotificacion(HistorialNotificaciones historial) {
        if (!historial.getMensajes().isEmpty()) {
            System.out.println(historial.getMensajes().get(historial.getMensajes().size() - 1));
        }
    }
}
