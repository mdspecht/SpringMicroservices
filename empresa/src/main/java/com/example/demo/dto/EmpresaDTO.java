package com.example.demo.dto;

import com.example.demo.entity.Empresa;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class EmpresaDTO {

    @NotBlank
    private String razaoSocial;
    @NotBlank
    private String nomeFantasia;
    @NotBlank
    @CNPJ(message = "Formato de CNPJ inválido")
    private String cnpj;
    private String ie;
    @NotBlank
    @Email(message = "Formato de email inválido")
    private String email;
    private String telefone;
    private String site;

    private EnderecoDTO endereco;
}
