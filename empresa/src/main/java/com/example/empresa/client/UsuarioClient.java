package com.example.empresa.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("usuario")
public interface UsuarioClient {

    @RequestMapping("/teste")
    String getTeste();
}
