package com.example.demo.service;

import com.example.demo.dto.PageDTO;
import com.example.demo.entity.Empresa;
import com.example.demo.entity.Endereco;
import com.example.demo.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Transactional(readOnly = true)
    public Page<Empresa> findAll(PageDTO pageDTO) {
        PageRequest pageRequest = PageRequest.of(pageDTO.getPage(), pageDTO.getSize());
        Page<Empresa> empresas = this.empresaRepository.findAll(pageRequest);
        return empresas;
    }

    @Transactional(readOnly = true)
    public Empresa findById(Long id) {
        Empresa empresa = this.empresaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return empresa;
    }

    @Transactional
    public Empresa create(Empresa empresa) {
        return this.empresaRepository.save(empresa);
    }

    @Transactional
    public Empresa update(Long id, Empresa empresa) {
        Empresa oldEmpresa = this.empresaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        oldEmpresa.setRazaoSocial(empresa.getRazaoSocial());
        oldEmpresa.setNomeFantasia(empresa.getNomeFantasia());
        oldEmpresa.setIe(empresa.getIe());
        oldEmpresa.setTelefone(empresa.getTelefone());
        oldEmpresa.setSite(empresa.getSite());
        oldEmpresa.setVendidoPor(empresa.getVendidoPor());
        oldEmpresa.setDescontoAte(empresa.getDescontoAte());

        Endereco oldEndereco = oldEmpresa.getEndereco();
        oldEndereco.setLogradouro(empresa.getEndereco().getLogradouro());
        oldEndereco.setNumero(empresa.getEndereco().getNumero());
        oldEndereco.setComplemento(empresa.getEndereco().getComplemento());
        oldEndereco.setBairro(empresa.getEndereco().getBairro());
        oldEndereco.setCidade(empresa.getEndereco().getCidade());
        oldEndereco.setEstado(empresa.getEndereco().getEstado());
        oldEndereco.setCep(empresa.getEndereco().getCep());

        return this.empresaRepository.save(oldEmpresa);
    }
}
