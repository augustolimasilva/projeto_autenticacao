package com.example.service;

import com.example.model.Usuario;

public interface UsuarioService {
	public Usuario procurarUsuarioPorEmail(String email);
	public void salvarUsuario(Usuario usuario);
}
