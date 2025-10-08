package com.platzi.platzi.market.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hola")
public class HolaMundoController {
    @GetMapping(value = "/mundo")
    public String holaMundo() {
        return "<h1 style='color:blue'>Hola Mundo 🚀</h1>";
    }
}
