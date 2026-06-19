package com.barrios.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller MVC.
 *
 * Recibe requests web simples y devuelve vistas Thymeleaf. No contiene logica
 * de negocio; mantiene separada la capa web de los servicios y del modelo de
 * dominio.
 */
@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "index";
    }
}
