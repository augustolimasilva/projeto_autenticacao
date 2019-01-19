package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Funcao;

@Repository("funcaoRepositorio")
public interface FuncaoRepositorio extends JpaRepository<Funcao, Integer>{
	Funcao procurarPorFuncao(String funcao);
}