package com.starking.prova.DTO;

import lombok.Data;

@Data
public class UsuarioRequest {

    private String nome;

    private String cpf;

    private String email;

    private String telefone;

    private String senha;
}
