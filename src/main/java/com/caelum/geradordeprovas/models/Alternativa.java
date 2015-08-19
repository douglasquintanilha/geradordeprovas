package com.caelum.geradordeprovas.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Alternativa {

	@Id
	@GeneratedValue
	private Long Id;
	
	@NotBlank
	private String descricao;
	
	
	private boolean alternativaCorreta = false;
	
	@ManyToOne()
	private Questao questao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getId() {
		return Id;
	}

	public boolean isAlternativaCorreta() {
		return alternativaCorreta;
	}

	public void setAlternativaCorreta(boolean alternativaCorreta) {
		this.alternativaCorreta = alternativaCorreta;
	}
	
	public void setQuestao(Questao questao) {
		this.questao = questao;
	}
	public Questao getQuestao() {
		return questao;
	}

	
}

