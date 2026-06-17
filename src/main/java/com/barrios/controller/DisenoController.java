package com.barrios.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DisenoController {
    @GetMapping("/diseno")
    public String diseno() {
        return "diseno";
    }
}
