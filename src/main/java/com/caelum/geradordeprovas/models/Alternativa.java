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
	private Long id;
	
	@NotBlank
	private String descricao;
	
	private boolean alternativaCorreta;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public boolean isAlternativaCorreta() {
		return alternativaCorreta;
	}

	public void setAlternativaCorreta(boolean alternativaCorreta) {
		this.alternativaCorreta = alternativaCorreta;
	}
		

	
}

