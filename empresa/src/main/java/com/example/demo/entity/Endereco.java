package com.example.demo.entity;

import com.example.demo.dto.EnderecoDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Data
@DynamicUpdate
@Entity
@NoArgsConstructor
public class Endereco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;

    public Endereco(EnderecoDTO enderecoDTO) {
        setLogradouro(enderecoDTO.getLogradouro());
        setNumero(enderecoDTO.getNumero());
        setComplemento(enderecoDTO.getComplemento());
        setBairro(enderecoDTO.getBairro());
        setCidade(enderecoDTO.getCidade());
        setEstado(enderecoDTO.getEstado());
        setCep(enderecoDTO.getCep());
    }
}
