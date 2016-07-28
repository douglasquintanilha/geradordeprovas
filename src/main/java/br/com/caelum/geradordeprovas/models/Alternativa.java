package br.com.caelum.geradordeprovas.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Alternativa {

	@Id
	@GeneratedValue
	private Long id;
	
	@Lob
	@NotBlank
	private String descricao;
	
	private boolean alternativaCorreta;

	public Alternativa(String descricao, boolean alternativaCorreta) {
		this.descricao = descricao;
		this.alternativaCorreta = alternativaCorreta;
	}
	
	public Alternativa(Long id, String descricao, boolean alternativaCorreta) {
		this.id = id;
		this.descricao = descricao;
		this.alternativaCorreta = alternativaCorreta;
	}
	
	public Alternativa() {
	}
	
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

