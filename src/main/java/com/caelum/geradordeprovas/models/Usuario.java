package com.caelum.geradordeprovas.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

@Entity
public class Usuario {

	@Id
	private String login;

	@Lob
	private String senha;

	private boolean admin;

	@ManyToMany
	private List<Prova> provas = new ArrayList<>();
	
	
	public void adicionaProvas(List<Prova> provas){
		this.provas.addAll(provas);
	}
	
	
	public List<Prova> getProvas() {
		return provas;
	}

	public void setProvas(List<Prova> provas) {
		this.provas = provas;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String nome) {
		this.login = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
