package com.brendon.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.brendon.models.Pessoa;
import com.brendon.service.PessoaService;

@RestController
@RequestMapping("pessoa")
public class PessoaController {
	
	@Autowired
	private PessoaService pessoaService;
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)	
	public Pessoa create(@RequestBody Pessoa pessoa) {
		return pessoaService.createPessoa(pessoa);
	}
	
	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<Pessoa>> findAll(){
		List<Pessoa> pessoasList =  pessoaService.findPessoas();
		if(pessoasList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			for(Pessoa pessoa: pessoasList) {
				int id = pessoa.getId();
				pessoa.add(linkTo(methodOn(PessoaController.class).findById(id)).withSelfRel());
			}
		}
		return new ResponseEntity<List<Pessoa>>(pessoasList, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Pessoa> findById(@PathVariable Integer id){
		Optional<Pessoa> pessoa =  pessoaService.findById(id);
		if(pessoa.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			pessoa.get().add(linkTo(methodOn(PessoaController.class).findAll()).withSelfRel());
			return new ResponseEntity<Pessoa>(pessoa.get(), HttpStatus.OK);
		}
	}
	
}
