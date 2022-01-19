package com.example.usuario.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    @GetMapping("/teste")
    public ResponseEntity<?> teste(){
        return new ResponseEntity("TESTE", HttpStatus.OK);
    }
}
