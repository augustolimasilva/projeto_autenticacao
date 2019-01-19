package com.example.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.Usuario;
import com.example.service.UsuarioService;

@Controller
public class LoginController {
	
	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping(value={"/", "/Login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("Login");
		return modelAndView;
	}
	
	
	@RequestMapping(value="/CadastroUsuario", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		Usuario usuario = new Usuario();
		modelAndView.addObject("usuario", usuario);
		modelAndView.setViewName("CadastroUsuario");
		return modelAndView;
	}
	
	@RequestMapping(value = "/CadastroUsuario", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid Usuario usuario, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		Usuario usuarioExiste = usuarioService.procurarUsuarioPorEmail(usuario.getEmail());
		if (usuarioExiste != null) {
			bindingResult
					.rejectValue("email", "error.usuario",
							"Já existe um usuário cadastrado, para o e-mail informado.");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("CadastroUsuario");
		} else {
			usuarioService.salvarUsuario(usuario);
			modelAndView.addObject("successMessage", "Usuário cadastrado com sucesso.");
			modelAndView.addObject("user", new Usuario());
			modelAndView.setViewName("CadastroUsuario");
			
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/admin/home", method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuarioService.procurarUsuarioPorEmail(auth.getName());
		modelAndView.addObject("userName", "Bem vindo " + usuario.getNome() + " " + usuario.getSobrenome() + " (" + usuario.getEmail() + ")");
		modelAndView.addObject("adminMessage","Conteúdo disponível somente para usuários do Financeiro.");
		modelAndView.setViewName("admin/home");
		return modelAndView;
	}
}
