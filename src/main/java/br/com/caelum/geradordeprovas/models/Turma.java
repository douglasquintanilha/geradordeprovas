package br.com.caelum.geradordeprovas.models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Turma {

	@Id
	@GeneratedValue
	private long id;

	private String nome;
	
	@OneToMany
	private Set<Usuario> usuarios;
	
	@OneToMany
	private Set<Prova> provas;

	public long getId() {
		return id;
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
