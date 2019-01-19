package com.example.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.model.Funcao;
import com.example.model.Usuario;
import com.example.repository.FuncaoRepositorio;
import com.example.repository.UsuarioRepositorio;

public class UsuarioServiceImp implements UsuarioService{
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	@Autowired
    private FuncaoRepositorio funcaoRepositorio;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public Usuario procurarUsuarioPorEmail(String email) {
		return usuarioRepositorio.procurarPorEmail(email);
	}

	@Override
	public void salvarUsuario(Usuario usuario) {
		usuario.setSenha(bCryptPasswordEncoder.encode(usuario.getSenha()));
		usuario.setAtivo(1);
        Funcao usuarioFuncao = funcaoRepositorio.procurarPorFuncao("ADMIN");
        usuario.setFuncoes(new HashSet<Funcao>(Arrays.asList(usuarioFuncao)));
		usuarioRepositorio.save(usuario);
	}
}
