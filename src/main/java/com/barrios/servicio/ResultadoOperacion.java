package com.barrios.servicio;

public class ResultadoOperacion<T> {
    private final boolean exitoso;
    private final String mensaje;
    private final T dato;

    private ResultadoOperacion(boolean exitoso, String mensaje, T dato) {
        this.exitoso = exitoso;
        this.mensaje = mensaje;
        this.dato = dato;
    }

    public static <T> ResultadoOperacion<T> ok(String mensaje, T dato) {
        return new ResultadoOperacion<>(true, mensaje, dato);
    }

    public static <T> ResultadoOperacion<T> error(String mensaje) {
        return new ResultadoOperacion<>(false, mensaje, null);
    }

    public boolean isExitoso() {
        return exitoso;
    }

    public String getMensaje() {
        return mensaje;
    }

    public T getDato() {
        return dato;
    }
}
