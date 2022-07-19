package com.example.empresa.controller;

import com.example.empresa.client.UsuarioClient;
import com.example.empresa.dto.EmpresaDTO;
import com.example.empresa.entity.Empresa;
import com.example.empresa.service.EmpresaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "API REST")
@CrossOrigin(origins = "*")
@RestController
public class EmpresaController {

    @Autowired
    EmpresaService empresaService;

    @ApiOperation(value = "Retorna todas empresas")
    @GetMapping
    public ResponseEntity<?> findAll(Pageable page) {
        Page<Empresa> empresas = this.empresaService.findAll(page);
        return new ResponseEntity<>(empresas, HttpStatus.OK);
    }

    @ApiOperation(value = "Retorna uma empresa pelo id")
    @GetMapping("/{id}")
//    @RolesAllowed({"user"})
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        Empresa empresa = this.empresaService.findById(id);
        return new ResponseEntity<>(empresa, HttpStatus.OK);
    }

    @ApiOperation(value = "Cadastra uma empresa")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid EmpresaDTO empresaDTO) {
        Empresa empresa = this.empresaService.create(new Empresa(empresaDTO));
        return new ResponseEntity<>(empresa, HttpStatus.OK);
    }

    @ApiOperation(value = "Atualiza uma empresa")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id,
                                    @RequestBody @Valid EmpresaDTO empresaDTO) {

        Empresa empresa = this.empresaService.update(id, new Empresa(empresaDTO));
        return new ResponseEntity<>("empresa", HttpStatus.OK);
    }

    @Autowired
    private UsuarioClient client;

    @GetMapping("/teste")
    public ResponseEntity teste() {
        String teste = client.getTeste();
        return new ResponseEntity(teste, HttpStatus.OK);
    }
}
