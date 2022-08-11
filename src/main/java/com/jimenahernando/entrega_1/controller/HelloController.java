package com.jimenahernando.entrega_1.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${server.port}")
    String port;

    @GetMapping("/hola")
    public String saludar(){
        return "Houston, tenemos un problema.";
    }

    @GetMapping("/puerto")
    public String conocerPuerto() {
        return "La aplicacion está corriendo por el puerto " + port;
    }
}
