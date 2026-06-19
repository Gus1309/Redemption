package com.barrios.proxy;

import com.barrios.modelo.Amenidad;
import com.barrios.modelo.AutorizacionVisita;
import com.barrios.modelo.Barrio;
import com.barrios.modelo.Expensa;
import com.barrios.modelo.Incidente;
import com.barrios.modelo.Novedad;
import com.barrios.modelo.Reclamo;
import com.barrios.modelo.ReservaAmenidad;
import com.barrios.modelo.Rol;
import com.barrios.modelo.Usuario;
import com.barrios.modelo.Visitante;
import com.barrios.modelo.Vivienda;
import com.barrios.servicio.ISistema;
import com.barrios.servicio.ResultadoOperacion;
import com.barrios.servicio.ResumenBarrio;

import java.util.List;

/**
 * Patron Proxy de proteccion.
 *
 * Intercepta las operaciones del sistema para validar permisos segun el rol
 * del usuario antes de delegar en el sistema real. En esta demo se controlan
 * los roles ADMINISTRADOR, PROPIETARIO, SEGURIDAD y TECNICO.
 */
public class SistemaProxy implements ISistema {
    private ISistema sistemaReal;

    public SistemaProxy(ISistema sistemaReal) {
        this.sistemaReal = sistemaReal;
    }

    @Override
    public ResultadoOperacion<Barrio> crearBarrio(Usuario usuario, Barrio barrio) {
        if (!puede(usuario, "CREAR_BARRIO")) {
            return denegar(usuario, "crear barrios");
        }
        return sistemaReal.crearBarrio(usuario, barrio);
    }

    @Override
    public ResultadoOperacion<Vivienda> registrarVivienda(Usuario usuario, Barrio barrio, Vivienda vivienda) {
        if (!puede(usuario, "GESTIONAR_VIVIENDAS")) {
            return denegar(usuario, "gestionar viviendas");
        }
        return sistemaReal.registrarVivienda(usuario, barrio, vivienda);
    }

    @Override
    public ResultadoOperacion<Amenidad> registrarAmenidad(Usuario usuario, Barrio barrio, Amenidad amenidad) {
        if (!puede(usuario, "GESTIONAR_AMENIDADES")) {
            return denegar(usuario, "gestionar amenidades");
        }
        return sistemaReal.registrarAmenidad(usuario, barrio, amenidad);
    }

    @Override
    public ResultadoOperacion<AutorizacionVisita> autorizarVisita(Usuario usuario, Barrio barrio, AutorizacionVisita autorizacionVisita) {
        if (!puede(usuario, "AUTORIZAR_VISITAS")) {
            return denegar(usuario, "autorizar visitas");
        }
        return sistemaReal.autorizarVisita(usuario, barrio, autorizacionVisita);
    }

    @Override
    public ResultadoOperacion<?> registrarIngreso(Usuario usuario, Barrio barrio, AutorizacionVisita autorizacionVisita) {
        if (!puede(usuario, "REGISTRAR_ACCESOS")) {
            return denegar(usuario, "registrar ingresos");
        }
        return sistemaReal.registrarIngreso(usuario, barrio, autorizacionVisita);
    }

    @Override
    public ResultadoOperacion<?> registrarEgreso(Usuario usuario, Barrio barrio, Visitante visitante) {
        if (!puede(usuario, "REGISTRAR_ACCESOS")) {
            return denegar(usuario, "registrar egresos");
        }
        return sistemaReal.registrarEgreso(usuario, barrio, visitante);
    }

    @Override
    public ResultadoOperacion<ReservaAmenidad> registrarReserva(Usuario usuario, Barrio barrio, ReservaAmenidad reservaAmenidad) {
        if (!puede(usuario, "RESERVAR_AMENIDADES")) {
            return denegar(usuario, "reservar amenidades");
        }
        return sistemaReal.registrarReserva(usuario, barrio, reservaAmenidad);
    }

    @Override
    public ResultadoOperacion<Reclamo> registrarReclamo(Usuario usuario, Barrio barrio, Reclamo reclamo) {
        if (!puede(usuario, "CREAR_RECLAMOS")) {
            return denegar(usuario, "crear reclamos");
        }
        return sistemaReal.registrarReclamo(usuario, barrio, reclamo);
    }

    @Override
    public ResultadoOperacion<Reclamo> avanzarReclamo(Usuario usuario, Reclamo reclamo) {
        if (!puede(usuario, "ATENDER_RECLAMOS")) {
            return denegar(usuario, "atender reclamos");
        }
        return sistemaReal.avanzarReclamo(usuario, reclamo);
    }

    @Override
    public ResultadoOperacion<Incidente> registrarIncidente(Usuario usuario, Barrio barrio, Incidente incidente) {
        if (!puede(usuario, "GESTIONAR_INCIDENTES")) {
            return denegar(usuario, "registrar incidentes");
        }
        return sistemaReal.registrarIncidente(usuario, barrio, incidente);
    }

    @Override
    public ResultadoOperacion<Incidente> actualizarIncidente(Usuario usuario, Incidente incidente, String nuevoEstado) {
        if (!puede(usuario, "GESTIONAR_INCIDENTES")) {
            return denegar(usuario, "actualizar incidentes");
        }
        return sistemaReal.actualizarIncidente(usuario, incidente, nuevoEstado);
    }

    @Override
    public ResultadoOperacion<Novedad> publicarNovedad(Usuario usuario, Barrio barrio, Novedad novedad) {
        if (!puede(usuario, "PUBLICAR_NOVEDADES")) {
            return denegar(usuario, "publicar novedades");
        }
        return sistemaReal.publicarNovedad(usuario, barrio, novedad);
    }

    @Override
    public ResultadoOperacion<Expensa> registrarExpensa(Usuario usuario, Barrio barrio, Expensa expensa) {
        if (!puede(usuario, "REGISTRAR_EXPENSAS")) {
            return denegar(usuario, "registrar expensas");
        }
        return sistemaReal.registrarExpensa(usuario, barrio, expensa);
    }

    @Override
    public ResultadoOperacion<ResumenBarrio> obtenerResumenBarrio(Usuario usuario, Barrio barrio) {
        if (!puede(usuario, "CONSULTAR")) {
            return denegar(usuario, "consultar informacion general");
        }
        return sistemaReal.obtenerResumenBarrio(usuario, barrio);
    }

    @Override
    public List<Barrio> listarBarrios(Usuario usuario) {
        return sistemaReal.listarBarrios(usuario);
    }

    public ISistema getSistemaReal() {
        return sistemaReal;
    }

    public void setSistemaReal(ISistema sistemaReal) {
        this.sistemaReal = sistemaReal;
    }

    private boolean puede(Usuario usuario, String operacion) {
        Rol rol = usuario.getRol();
        return switch (operacion) {
            case "CREAR_BARRIO", "GESTIONAR_VIVIENDAS", "GESTIONAR_AMENIDADES",
                    "PUBLICAR_NOVEDADES", "REGISTRAR_EXPENSAS", "CONSULTAR" -> rol == Rol.ADMINISTRADOR;
            case "AUTORIZAR_VISITAS", "RESERVAR_AMENIDADES", "CREAR_RECLAMOS" -> rol == Rol.PROPIETARIO;
            case "REGISTRAR_ACCESOS" -> rol == Rol.SEGURIDAD;
            case "GESTIONAR_INCIDENTES", "ATENDER_RECLAMOS" -> rol == Rol.TECNICO;
            default -> false;
        };
    }

    private <T> ResultadoOperacion<T> denegar(Usuario usuario, String operacion) {
        return ResultadoOperacion.error("[DENEGADO] El usuario " + usuario.getRol()
                + " no puede " + operacion);
    }
}
