package com.pessoa.model;

import jakarta.persistence.Entity; // Anotação Entity
import jakarta.persistence.Id; // Anotação Id
import lombok.Data; // Anotação Data
import java.util.UUID; // classe UUID

@Data // Reduz boilerplate
@Entity
public class Pessoa {

    @Id
    private String id;

    private String nome;
    private int idade;
    private String cpf;

    // Construtor padrão que inicializa o Id
    public Pessoa() {
        this.id = UUID.randomUUID().toString();
    }

}
