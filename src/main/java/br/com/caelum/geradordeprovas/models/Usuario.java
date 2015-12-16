package br.com.caelum.geradordeprovas.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;

@Entity
public class Usuario {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(unique=true)
	@Size(min = 5, max = 30)
	private String login;

	@Column(length=2048)
	@Size(min = 5)
	private String senha;


	private boolean admin;

	@ManyToMany
	private List<Prova> provas = new ArrayList<>();

	public void adicionaProvas(List<Prova> provas) {
		this.provas.addAll(provas);
	}

	public List<Long> getProvasIds(){
		List<Long> provaIds = new ArrayList<>();
		for(Prova prova : provas){
			provaIds.add(prova.getId());
		}
		return provaIds;
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
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


}
