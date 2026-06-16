package com.barrios.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Barrio {
    private Long id;
    private String nombre;
    private String descripcion;
    private final List<Vivienda> viviendas = new ArrayList<>();
    private final List<Amenidad> amenidades = new ArrayList<>();
    private final List<Visitante> visitantes = new ArrayList<>();
    private final List<AutorizacionVisita> autorizaciones = new ArrayList<>();
    private final List<RegistroAcceso> accesos = new ArrayList<>();
    private final List<ReservaAmenidad> reservas = new ArrayList<>();
    private final List<Reclamo> reclamos = new ArrayList<>();
    private final List<Incidente> incidentes = new ArrayList<>();
    private final List<Novedad> novedades = new ArrayList<>();
    private final List<Expensa> expensas = new ArrayList<>();

    public Barrio() {
    }

    public Barrio(Long id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Vivienda> getViviendas() {
        return Collections.unmodifiableList(viviendas);
    }

    public void agregarVivienda(Vivienda vivienda) {
        viviendas.add(vivienda);
    }

    public List<Amenidad> getAmenidades() {
        return Collections.unmodifiableList(amenidades);
    }

    public void agregarAmenidad(Amenidad amenidad) {
        amenidades.add(amenidad);
    }

    public List<Visitante> getVisitantes() {
        return Collections.unmodifiableList(visitantes);
    }

    public void agregarVisitante(Visitante visitante) {
        if (!visitantes.contains(visitante)) {
            visitantes.add(visitante);
        }
    }

    public List<AutorizacionVisita> getAutorizaciones() {
        return Collections.unmodifiableList(autorizaciones);
    }

    public void agregarAutorizacion(AutorizacionVisita autorizacion) {
        autorizaciones.add(autorizacion);
    }

    public List<RegistroAcceso> getAccesos() {
        return Collections.unmodifiableList(accesos);
    }

    public void agregarAcceso(RegistroAcceso acceso) {
        accesos.add(acceso);
    }

    public List<ReservaAmenidad> getReservas() {
        return Collections.unmodifiableList(reservas);
    }

    public void agregarReserva(ReservaAmenidad reserva) {
        reservas.add(reserva);
    }

    public List<Reclamo> getReclamos() {
        return Collections.unmodifiableList(reclamos);
    }

    public void agregarReclamo(Reclamo reclamo) {
        reclamos.add(reclamo);
    }

    public List<Incidente> getIncidentes() {
        return Collections.unmodifiableList(incidentes);
    }

    public void agregarIncidente(Incidente incidente) {
        incidentes.add(incidente);
    }

    public List<Novedad> getNovedades() {
        return Collections.unmodifiableList(novedades);
    }

    public void agregarNovedad(Novedad novedad) {
        novedades.add(novedad);
    }

    public List<Expensa> getExpensas() {
        return Collections.unmodifiableList(expensas);
    }

    public void agregarExpensa(Expensa expensa) {
        expensas.add(expensa);
    }
}
