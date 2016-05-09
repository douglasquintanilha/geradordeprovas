package br.com.caelum.geradordeprovas.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
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

	@Column(unique = true)
	@Size(min = 3, max = 100)
	private String login;

	@Column(length = 2048)
	@Size(min = 5)
	private String senha;

	private boolean admin;

	@ManyToMany
	private Set<Prova> provas = new HashSet<>();
	
	//--------------Atributos refatoraçáo
	
	@ElementCollection(targetClass=Avaliacao.class)
	private Set<Avaliacao> avaliacoes = new HashSet<>();

	public void adicionaAvaliacoes(Collection<Avaliacao> avaliacoes){
		this.avaliacoes.addAll(avaliacoes);
	}
	
	public void adicionaProvas(List<Prova> provas) {
		this.provas.addAll(provas);
	}

	public List<Long> getProvasIds() {
		List<Long> provaIds = new ArrayList<>();
		for (Prova prova : provas) {
			provaIds.add(prova.getId());
		}
		return provaIds;
	}

	public Set<Prova> getProvas() {
		return provas;
	}

	public void setProvas(List<Prova> provas) {
		Set<Prova> provasSet = new HashSet<>(provas);
		this.provas = provasSet;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
