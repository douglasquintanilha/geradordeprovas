package com.caelum.geradordeprovas.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Prova {
	
	@Id
	@GeneratedValue
	private Long id;

	@NotBlank
	private String nome;
	
//	private String autor;
	
	@NotEmpty( message = "A prova deve conter pelo menos uma questão!")
	@ManyToMany()
	private List<Questao> questoes;

//	public String getAutor() {
//		return autor;
//	}
//
//	public void setAutor(String autor) {
//		this.autor = autor;
//	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Questao> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(List<Questao> questoes) {
		this.questoes = questoes;
	}

}
