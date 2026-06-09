package com.barrios.main;

import com.barrios.proxy.SistemaProxy;
import com.barrios.servicio.GestionPrincipal;
import com.barrios.servicio.ISistema;

public class Main {
    public static void main(String[] args) {
        System.out.println("Sistema de Gestion de Barrios Privados iniciado");

        GestionPrincipal gestionPrincipal = GestionPrincipal.getInstancia();
        ISistema sistema = new SistemaProxy(gestionPrincipal);

        System.out.println("Sistema inicializado correctamente: " + sistema.getClass().getSimpleName());
    }
}
