package com.example.model;

import javax.persistence.Column;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "email")
	@Email(message = "É necessário informar um email válido.")
	@NotEmpty(message = "É necessário informar um Email.")
	private String email;
	
	@Column(name = "senha")
	@Length(min = 5, message = "A senha deve ter no minimo 5 caracteres.")
	@NotEmpty(message = "É necessário informar uma senha.")
	@Transient
	private String senha;
	
	@Column(name = "nome")
	@NotEmpty(message = "É necessário informar um nome")
	private String nome;
	
	@Column(name = "sobrenome")
	@NotEmpty(message = "É necessário informar um sobrenome")
	private String sobrenome;
	
	@Column(name = "ativo")
	private int ativo;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "usuario", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "id"))
	private Set<Funcao> funcoes;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}

	public Set<Funcao> getFuncoes() {
		return funcoes;
	}

	public void setFuncoes(Set<Funcao> funcoes) {
		this.funcoes = funcoes;
	}
}