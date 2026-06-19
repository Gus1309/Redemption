package com.barrios.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller MVC para la vista de diseno.
 *
 * Expone una ruta web y devuelve una vista Thymeleaf sin incorporar logica de
 * negocio, manteniendo la responsabilidad de la clase acotada a la capa web.
 */
@Controller
public class DisenoController {
    @GetMapping("/diseno")
    public String diseno() {
        return "diseno";
    }
}
