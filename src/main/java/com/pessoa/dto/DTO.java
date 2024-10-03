package com.pessoa.dto;

import lombok.Data;

@Data
public class DTO {

    private String id;
    private String nome;
    private int idade;
    private String cpf;

    public DTO(String id, String nome, int idade, String cpf) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
    }

}
