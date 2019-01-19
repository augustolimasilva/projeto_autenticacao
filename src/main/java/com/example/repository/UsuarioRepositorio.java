package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Usuario;

@Repository("usuarioRepositorio")
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
	Usuario procurarPorEmail(String email);
}
