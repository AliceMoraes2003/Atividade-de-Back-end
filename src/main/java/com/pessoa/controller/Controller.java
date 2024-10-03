package com.pessoa.controller;

import com.pessoa.model.Pessoa;
import com.pessoa.repository.PessoaRepository;
import com.pessoa.dto.DTO;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.bind.annotation.*;

import java.util.List; 
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pessoas")
public class Controller {

    private final PessoaRepository pessoaRepository;

    @Autowired
    public Controller(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @PostMapping
    public DTO criarPessoa(@RequestBody DTO pessoaDTO) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setIdade(pessoaDTO.getIdade());
        pessoa.setCpf(pessoaDTO.getCpf());

        pessoa = pessoaRepository.save(pessoa);

        return new DTO(pessoa.getId(), pessoa.getNome(), pessoa.getIdade(), pessoa.getCpf());
    }

    @GetMapping
    public List<DTO> listarPessoas() {
        return pessoaRepository.findAll().stream()
                .map(pessoa -> new DTO(pessoa.getId(), pessoa.getNome(), pessoa.getIdade(), pessoa.getCpf()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public DTO buscarPessoaPorId(@PathVariable String id) {
        return pessoaRepository.findById(id)
                .map(pessoa -> new DTO(pessoa.getId(), pessoa.getNome(), pessoa.getIdade(), pessoa.getCpf()))
                .orElse(null);
    }

}
