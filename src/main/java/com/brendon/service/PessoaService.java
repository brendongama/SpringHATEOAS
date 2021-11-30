package com.brendon.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brendon.models.Pessoa;
import com.brendon.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Pessoa createPessoa(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}
	
	public List<Pessoa> findPessoas(){
		return pessoaRepository.findAll();
	}

	public Optional<Pessoa> findById(int id) {
		return pessoaRepository.findById(id);
	}

}
