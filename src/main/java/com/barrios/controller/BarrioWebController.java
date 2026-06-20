package com.barrios.controller;

import com.barrios.modelo.Barrio;
import com.barrios.modelo.Reclamo;
import com.barrios.servicio.DatosDemoService;
import com.barrios.servicio.ResultadoOperacion;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

/**
 * Controller MVC para las vistas de detalle de barrio.
 *
 * Atiende rutas web, carga datos en el Model y retorna vistas Thymeleaf para
 * visitas, accesos, reservas, reclamos, incidentes, novedades y expensas. La
 * logica se mantiene liviana y delegada en servicios, preservando la
 * separacion entre capa web, servicios y modelo de dominio.
 */
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

    @GetMapping("/barrios/{id}/accesos")
    public String accesos(@PathVariable Long id, Model model) {
        Barrio barrio = cargarBarrio(id, model);
        model.addAttribute("accesos", barrio.getAccesos());
        return "accesos";
    }

    @GetMapping("/barrios/{id}/reservas")
    public String reservas(@PathVariable Long id, Model model) {
        Barrio barrio = cargarBarrio(id, model);
        model.addAttribute("reservas", barrio.getReservas());
        return "reservas";
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
    public String formularioNuevoReclamo(@PathVariable Long id, Model model) {
        cargarBarrio(id, model);
        return "reclamo-form";
    }

    @PostMapping("/barrios/{id}/reclamos")
    public String crearReclamo(@PathVariable Long id,
                               @RequestParam String descripcion,
                               Model model) {
        Barrio barrio = cargarBarrio(id, model);

        Reclamo reclamo = new Reclamo();
        reclamo.setId(generarProximoId(barrio));
        reclamo.setDescripcion(descripcion);
        reclamo.setFecha(LocalDate.now());

        ResultadoOperacion<Reclamo> resultado = datosDemoService.crearReclamo(barrio, reclamo);

        if (!resultado.isExitoso()) {
            model.addAttribute("error", resultado.getMensaje());
            return "reclamo-form";
        }

        return "redirect:/barrios/" + id + "/reclamos";
    }

    @GetMapping("/barrios/{id}/incidentes")
    public String incidentes(@PathVariable Long id, Model model) {
        Barrio barrio = cargarBarrio(id, model);
        model.addAttribute("incidentes", barrio.getIncidentes());
        return "incidentes";
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

    private long generarProximoId(Barrio barrio) {
        return barrio.getReclamos().size() + 1L;
    }
}