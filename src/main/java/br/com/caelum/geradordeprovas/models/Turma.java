package br.com.caelum.geradordeprovas.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Turma {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	private String nome;

	@ManyToMany
	private Set<Usuario> usuarios;

	@ManyToMany
	private Set<Prova> provas;

	public void adicionaProvas(List<Prova> provas){
		this.provas.addAll(provas);
		atualizaProvasUsuarios();
	}
	
	public void atualizaProvasUsuarios(){
		List<Prova> provasList = new ArrayList<Prova>(provas);
		for(Usuario usuario : usuarios){
			usuario.adicionaProvas(provasList);
		}
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public void setProvas(Set<Prova> provas) {
		this.provas = provas;
	}

	
	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public Set<Prova> getProvas() {
		return provas;
	}

	public String getNome() {
		return nome;
	}
}
