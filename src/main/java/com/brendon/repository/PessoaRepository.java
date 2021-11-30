package com.brendon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brendon.models.Pessoa;

@Repository
public interface PessoaRepository  extends JpaRepository<Pessoa, Integer>{

}
