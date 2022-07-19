package com.example.empresa.service;

import com.example.empresa.entity.Empresa;
import com.example.empresa.entity.Endereco;
import com.example.empresa.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Transactional(readOnly = true)
    public Page<Empresa> findAll(Pageable page) {
        PageRequest pageRequest = PageRequest.of(page.getPageNumber(), page.getPageSize());
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
        oldEmpresa.setEmail(empresa.getEmail());
        oldEmpresa.setSite(empresa.getSite());

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
