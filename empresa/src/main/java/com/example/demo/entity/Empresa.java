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
    @Column(length = 255)
    private String ie;
    @Column(length = 255)
    private String telefone;
    @Column(length = 255)
    private String site;
    @Column(length = 255)
    private String vendidoPor;
    @Column(length = 255)
    private String descontoAte;

    @Column(nullable = false)
    private Boolean indAtivo = true;
    @Column(nullable = false)
    private Boolean indPausado = false;

    @UpdateTimestamp
    private Date dataAlteracao;

    @OneToOne
    private Endereco endereco;

    private String email;

    public Empresa(EmpresaDTO empresaDTO) {
        this.setRazaoSocial(empresaDTO.getRazaoSocial());
        this.setNomeFantasia(empresaDTO.getNomeFantasia());
        this.setCnpj(empresaDTO.getCnpj());
        this.setIe(empresaDTO.getIe());
        this.setTelefone(empresaDTO.getTelefone());
        this.setSite(empresaDTO.getSite());
        this.setVendidoPor(empresaDTO.getVendidoPor());
        this.setDescontoAte(empresaDTO.getDescontoAte());
        setEndereco(new Endereco(empresaDTO.getEndereco()));
    }
}
