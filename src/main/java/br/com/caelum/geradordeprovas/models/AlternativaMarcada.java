package br.com.caelum.geradordeprovas.models;

import javax.persistence.Embeddable;
import javax.persistence.Lob;

import org.hibernate.validator.constraints.NotBlank;

@Embeddable
public class AlternativaMarcada {

	private Long id;

	@Lob
	@NotBlank
	private String descricao;

	private boolean alternativaCorreta;

	public AlternativaMarcada() {

	}

	public AlternativaMarcada(Long id, String descricao, boolean alternativaCorreta) {
		this.id = id;
		this.descricao = descricao;
		this.alternativaCorreta = alternativaCorreta;
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

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isAlternativaCorreta() {
		return alternativaCorreta;
	}

	public void setAlternativaCorreta(boolean alternativaCorreta) {
		this.alternativaCorreta = alternativaCorreta;
	}

}
