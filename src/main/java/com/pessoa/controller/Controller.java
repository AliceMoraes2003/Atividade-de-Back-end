package com.pessoa.controller;

import com.pessoa.model.Pessoa; // Importa o model
import com.pessoa.repository.PessoaRepository; // Importa o repositório
import com.pessoa.dto.DTO; // Importa o DTO
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pessoas")
public class Controller {

    private final PessoaRepository pessoaRepository;

    // Injeção de dependência via construtor
    @Autowired
    public Controller(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    // Método POST para criar uma nova Pessoa
    @PostMapping
    public DTO criarPessoa(@RequestBody DTO pessoaDTO) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setIdade(pessoaDTO.getIdade());
        pessoa.setCpf(pessoaDTO.getCpf());

        pessoa = pessoaRepository.save(pessoa); // Salva a pessoa no banco de dados

        return new DTO(pessoa.getId(), pessoa.getNome(), pessoa.getIdade(), pessoa.getCpf()); // Retorna o DTO com os dados da pessoa criada
    }

    // Método GET para buscar todas as pessoas
    @GetMapping
    public List<DTO> listarPessoas() {
        return pessoaRepository.findAll().stream()
                .map(pessoa -> new DTO(pessoa.getId(), pessoa.getNome(), pessoa.getIdade(), pessoa.getCpf())) // Mapeia cada Pessoa para PessoaDTO
                .collect(Collectors.toList()); // Coleta os DTOs em uma lista
    }

    // Método GET para buscar uma Pessoa pelo ID
    @GetMapping("/{id}") // Define que o método responde a GET requests no endpoint "/api/pessoas/{id}"
    public DTO buscarPessoaPorId(@PathVariable String id) {
        return pessoaRepository.findById(id) // Busca a pessoa pelo ID no repositório
                .map(pessoa -> new DTO(pessoa.getId(), pessoa.getNome(), pessoa.getIdade(), pessoa.getCpf())) // Mapeia para DTO
                .orElse(null); // Retorna null se não encontrar
    }


}
