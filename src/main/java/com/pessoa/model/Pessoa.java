package com.pessoa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import java.util.UUID;

@Data
@Entity
public class Pessoa {

    @Id
    private String id;

    private String nome;
    private int idade;
    private String cpf;

    public Pessoa() {
        this.id = UUID.randomUUID().toString();
    }

}
