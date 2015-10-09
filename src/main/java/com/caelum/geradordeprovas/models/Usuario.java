package com.caelum.geradordeprovas.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;

@Entity
public class Usuario {

	@Id
	@Size(min = 5, max = 30, message = "Minimo de 5 e maximo de 30")
	private String login;

	@Lob
	@Size(min = 5)
	private String senha;

	private boolean admin;

	@ManyToMany
	private List<Prova> provas = new ArrayList<>();

	public void adicionaProvas(List<Prova> provas) {

		List<Prova> provasPraAdicionar = new ArrayList<>();
		
		for (Prova prova : provas) {
			for (Prova thisprova : this.provas) {
				if (thisprova.getId() == prova.getId()) {
					// ja tem essa prova na lista
				} else {
					provasPraAdicionar.add(prova);
				}
			}
		}
		
		this.provas.addAll(provasPraAdicionar);
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
