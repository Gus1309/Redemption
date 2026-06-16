package com.barrios.controller;

import com.barrios.servicio.DatosDemoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    private final DatosDemoService datosDemoService;

    public DashboardController(DatosDemoService datosDemoService) {
        this.datosDemoService = datosDemoService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("barrios", datosDemoService.obtenerDashboard());
        return "dashboard";
    }
}
