package com.barrios.controller;

import com.barrios.estado.EstadoPendiente;
import com.barrios.modelo.Barrio;
import com.barrios.modelo.Incidente;
import com.barrios.modelo.Reclamo;
import com.barrios.servicio.DatosDemoService;
import com.barrios.servicio.ResultadoOperacion;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

@Controller
public class BarrioWebController {
    private final DatosDemoService datosDemoService;

    public BarrioWebController(DatosDemoService datosDemoService) {
        this.datosDemoService = datosDemoService;
    }

    @GetMapping("/barrios/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        Barrio barrio = cargarBarrio(id, model);
        model.addAttribute("resumen", datosDemoService.obtenerResumen(barrio));
        return "barrio";
    }

    @GetMapping("/barrios/{id}/visitas")
    public String visitas(@PathVariable Long id, Model model) {
        Barrio barrio = cargarBarrio(id, model);
        model.addAttribute("visitas", barrio.getAutorizaciones());
        return "visitas";
    }

    @GetMapping("/barrios/{id}/visitas/nueva")
    public String formularioNuevaVisita(@PathVariable Long id,
                                        @RequestParam(required = false) String rol,
                                        Model model) {
        cargarBarrio(id, model);
        if (!esPropietario(rol)) {
            return "redirect:/barrios/" + id + "/visitas" + queryRol(rol);
        }
        return "visita-form";
    }

    @PostMapping("/barrios/{id}/visitas")
    public String crearVisita(@PathVariable Long id,
                              @RequestParam String nombreVisitante,
                              @RequestParam String documento,
                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDesde,
                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaHasta,
                              @RequestParam(required = false) String rol,
                              Model model) {
        Barrio barrio = cargarBarrio(id, model);
        if (!esPropietario(rol)) {
            return "redirect:/barrios/" + id + "/visitas" + queryRol(rol);
        }

        ResultadoOperacion<?> resultado = datosDemoService.crearAutorizacionVisita(
                barrio, nombreVisitante, documento, fechaDesde, fechaHasta);

        if (!resultado.isExitoso()) {
            model.addAttribute("error", resultado.getMensaje());
            return "visita-form";
        }

        return "redirect:/barrios/" + id + "/visitas" + queryRol(rol);
    }

    @GetMapping("/barrios/{id}/accesos")
    public String accesos(@PathVariable Long id, Model model) {
        Barrio barrio = cargarBarrio(id, model);
        model.addAttribute("accesos", barrio.getAccesos());
        model.addAttribute("autorizaciones", barrio.getAutorizaciones());
        return "accesos";
    }

    @PostMapping("/barrios/{id}/accesos/{autorizacionId}/ingreso")
    public String registrarIngreso(@PathVariable Long id,
                                   @PathVariable Long autorizacionId,
                                   @RequestParam(required = false) String rol,
                                   Model model) {
        Barrio barrio = cargarBarrio(id, model);
        if (!esSeguridad(rol)) {
            return "redirect:/barrios/" + id + "/accesos" + queryRol(rol);
        }

        ResultadoOperacion<?> resultado = datosDemoService.registrarIngreso(barrio, autorizacionId);

        if (!resultado.isExitoso()) {
            model.addAttribute("accesos", barrio.getAccesos());
            model.addAttribute("autorizaciones", barrio.getAutorizaciones());
            model.addAttribute("error", resultado.getMensaje());
            return "accesos";
        }

        return "redirect:/barrios/" + id + "/accesos" + queryRol(rol);
    }

    @PostMapping("/barrios/{id}/accesos/{autorizacionId}/egreso")
    public String registrarEgreso(@PathVariable Long id,
                                  @PathVariable Long autorizacionId,
                                  @RequestParam(required = false) String rol,
                                  Model model) {
        Barrio barrio = cargarBarrio(id, model);
        if (!esSeguridad(rol)) {
            return "redirect:/barrios/" + id + "/accesos" + queryRol(rol);
        }

        ResultadoOperacion<?> resultado = datosDemoService.registrarEgreso(barrio, autorizacionId);

        if (!resultado.isExitoso()) {
            model.addAttribute("accesos", barrio.getAccesos());
            model.addAttribute("autorizaciones", barrio.getAutorizaciones());
            model.addAttribute("error", resultado.getMensaje());
            return "accesos";
        }

        return "redirect:/barrios/" + id + "/accesos" + queryRol(rol);
    }

    @GetMapping("/barrios/{id}/reservas")
    public String reservas(@PathVariable Long id, Model model) {
        Barrio barrio = cargarBarrio(id, model);
        model.addAttribute("reservas", barrio.getReservas());
        return "reservas";
    }

    @GetMapping("/barrios/{id}/reservas/nueva")
    public String formularioNuevaReserva(@PathVariable Long id,
                                         @RequestParam(required = false) String rol,
                                         Model model) {
        Barrio barrio = cargarBarrio(id, model);
        if (!esPropietario(rol)) {
            return "redirect:/barrios/" + id + "/reservas" + queryRol(rol);
        }
        model.addAttribute("amenidades", barrio.getAmenidades());
        return "reserva-form";
    }

    @PostMapping("/barrios/{id}/reservas")
    public String crearReserva(@PathVariable Long id,
                               @RequestParam Long amenidadId,
                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
                               @RequestParam(required = false) String rol,
                               Model model) {
        Barrio barrio = cargarBarrio(id, model);
        if (!esPropietario(rol)) {
            return "redirect:/barrios/" + id + "/reservas" + queryRol(rol);
        }

        ResultadoOperacion<?> resultado = datosDemoService.crearReserva(barrio, amenidadId, fecha);

        if (!resultado.isExitoso()) {
            model.addAttribute("amenidades", barrio.getAmenidades());
            model.addAttribute("error", resultado.getMensaje());
            return "reserva-form";
        }

        return "redirect:/barrios/" + id + "/reservas" + queryRol(rol);
    }

    @GetMapping("/barrios/{id}/reclamos")
    public String reclamos(@PathVariable Long id, Model model) {
        Barrio barrio = cargarBarrio(id, model);
        List<Reclamo> reclamos = barrio.getReclamos();
        model.addAttribute("pendientes", filtrarPorEstado(reclamos, "PENDIENTE"));
        model.addAttribute("enProceso", filtrarPorEstado(reclamos, "EN_PROCESO"));
        model.addAttribute("resueltos", filtrarPorEstado(reclamos, "RESUELTO"));
        return "reclamos";
    }

    @GetMapping("/barrios/{id}/reclamos/nuevo")
    public String formularioNuevoReclamo(@PathVariable Long id,
                                         @RequestParam(required = false) String rol,
                                         Model model) {
        cargarBarrio(id, model);
        if (!esPropietario(rol)) {
            return "redirect:/barrios/" + id + "/reclamos" + queryRol(rol);
        }
        return "reclamo-form";
    }

    @PostMapping("/barrios/{id}/reclamos")
    public String crearReclamo(@PathVariable Long id,
                               @RequestParam String descripcion,
                               @RequestParam(required = false) String rol,
                               Model model) {
        Barrio barrio = cargarBarrio(id, model);
        if (!esPropietario(rol)) {
            return "redirect:/barrios/" + id + "/reclamos" + queryRol(rol);
        }

        Reclamo reclamo = new Reclamo(generarProximoIdReclamo(barrio), descripcion, LocalDate.now(), new EstadoPendiente());
        ResultadoOperacion<Reclamo> resultado = datosDemoService.crearReclamo(barrio, reclamo);

        if (!resultado.isExitoso()) {
            model.addAttribute("error", resultado.getMensaje());
            return "reclamo-form";
        }

        return "redirect:/barrios/" + id + "/reclamos" + queryRol(rol);
    }

    @PostMapping("/barrios/{id}/reclamos/{reclamoId}/avanzar")
    public String avanzarReclamo(@PathVariable Long id,
                                 @PathVariable Long reclamoId,
                                 @RequestParam(required = false) String rol,
                                 Model model) {
        Barrio barrio = cargarBarrio(id, model);
        if (!esSeguridad(rol)) {
            return "redirect:/barrios/" + id + "/reclamos" + queryRol(rol);
        }

        datosDemoService.avanzarReclamo(barrio, reclamoId);
        return "redirect:/barrios/" + id + "/reclamos" + queryRol(rol);
    }

    @GetMapping("/barrios/{id}/incidentes")
    public String incidentes(@PathVariable Long id, Model model) {
        Barrio barrio = cargarBarrio(id, model);
        model.addAttribute("incidentes", barrio.getIncidentes());
        return "incidentes";
    }

    @GetMapping("/barrios/{id}/incidentes/nuevo")
    public String formularioNuevoIncidente(@PathVariable Long id,
                                           @RequestParam(required = false) String rol,
                                           Model model) {
        cargarBarrio(id, model);
        if (!esSeguridad(rol)) {
            return "redirect:/barrios/" + id + "/incidentes" + queryRol(rol);
        }
        return "incidente-form";
    }

    @PostMapping("/barrios/{id}/incidentes")
    public String crearIncidente(@PathVariable Long id,
                                 @RequestParam String descripcion,
                                 @RequestParam(required = false) String rol,
                                 Model model) {
        Barrio barrio = cargarBarrio(id, model);
        if (!esSeguridad(rol)) {
            return "redirect:/barrios/" + id + "/incidentes" + queryRol(rol);
        }

        Incidente incidente = new Incidente(generarProximoIdIncidente(barrio), descripcion, LocalDate.now(), "ABIERTO");
        ResultadoOperacion<Incidente> resultado = datosDemoService.crearIncidente(barrio, incidente);

        if (!resultado.isExitoso()) {
            model.addAttribute("error", resultado.getMensaje());
            return "incidente-form";
        }

        return "redirect:/barrios/" + id + "/incidentes" + queryRol(rol);
    }

    @PostMapping("/barrios/{id}/incidentes/{incidenteId}/estado")
    public String actualizarIncidente(@PathVariable Long id,
                                      @PathVariable Long incidenteId,
                                      @RequestParam String estado,
                                      @RequestParam(required = false) String rol,
                                      Model model) {
        Barrio barrio = cargarBarrio(id, model);
        if (!esTecnico(rol)) {
            return "redirect:/barrios/" + id + "/incidentes" + queryRol(rol);
        }

        datosDemoService.actualizarIncidente(barrio, incidenteId, estado);
        return "redirect:/barrios/" + id + "/incidentes" + queryRol(rol);
    }

    @GetMapping("/barrios/{id}/novedades")
    public String novedades(@PathVariable Long id, Model model) {
        Barrio barrio = cargarBarrio(id, model);
        model.addAttribute("novedades", barrio.getNovedades());
        return "novedades";
    }

    @GetMapping("/barrios/{id}/expensas")
    public String expensas(@PathVariable Long id, Model model) {
        Barrio barrio = cargarBarrio(id, model);
        model.addAttribute("expensas", barrio.getExpensas());
        return "expensas";
    }

    private Barrio cargarBarrio(Long id, Model model) {
        Barrio barrio = datosDemoService.buscarBarrio(id)
                .orElseThrow(() -> new IllegalArgumentException("Barrio inexistente: " + id));
        model.addAttribute("barrio", barrio);
        return barrio;
    }

    private List<Reclamo> filtrarPorEstado(List<Reclamo> reclamos, String estado) {
        return reclamos.stream()
                .filter(reclamo -> estado.equals(reclamo.getEstado().getNombre()))
                .toList();
    }

    private Long generarProximoIdReclamo(Barrio barrio) {
        return barrio.getReclamos().stream()
                .mapToLong(Reclamo::getId)
                .max()
                .orElse(0L) + 1L;
    }

    private Long generarProximoIdIncidente(Barrio barrio) {
        return barrio.getIncidentes().stream()
                .mapToLong(Incidente::getId)
                .max()
                .orElse(0L) + 1L;
    }

    private String queryRol(String rol) {
        if (rol == null || rol.isBlank()) {
            return "";
        }
        return "?rol=" + UriUtils.encode(rol, StandardCharsets.UTF_8);
    }

    private boolean esPropietario(String rol) {
        return "PROPIETARIO".equals(rol);
    }

    private boolean esSeguridad(String rol) {
        return "SEGURIDAD".equals(rol);
    }

    private boolean esTecnico(String rol) {
        return "TECNICO".equals(rol);
    }
}