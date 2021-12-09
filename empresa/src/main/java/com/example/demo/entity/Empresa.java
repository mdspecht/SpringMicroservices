package com.example.demo.entity;

import com.example.demo.dto.EmpresaDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@DynamicUpdate
@Entity
@NoArgsConstructor
public class Empresa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 255, nullable = false)
    private String razaoSocial;
    @Column(length = 255, nullable = false)
    private String nomeFantasia;
    @Column(length = 14, nullable = false, unique = true)
    private String cnpj;
    @Column(length = 255) //***
    private String ie;
    @Column(length = 255) //***
    private String telefone;
    @Column(length = 255)
    private String email;
    @Column(length = 255)
    private String site;

    @Column(nullable = false)
    private Boolean indAtivo = true;

    @OneToOne(cascade = {CascadeType.ALL})
    private Endereco endereco;

    @UpdateTimestamp
    private Date dataAlteracao;

    public Empresa(EmpresaDTO empresaDTO) {
        this.setRazaoSocial(empresaDTO.getRazaoSocial());
        this.setNomeFantasia(empresaDTO.getNomeFantasia());
        this.setCnpj(empresaDTO.getCnpj());
        this.setIe(empresaDTO.getIe());
        this.setTelefone(empresaDTO.getTelefone());
        this.setEmail(empresaDTO.getEmail());
        this.setSite(empresaDTO.getSite());
        setEndereco(new Endereco(empresaDTO.getEndereco()));
    }
}
