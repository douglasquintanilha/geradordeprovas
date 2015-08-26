package com.caelum.geradordeprovas.models;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Questao {

	@Id
	@GeneratedValue
	private Long id;

	@NotBlank
	private String titulo;

	@Valid
	@Transient
	private List<Alternativa> alternativa;

	@NotBlank
	@Transient
	private String alternativaCorreta;
	

	public String getAlternativaCorreta() {
		return alternativaCorreta;
	}

	public void setAlternativaCorreta(String alternativaCorreta) {
		this.alternativaCorreta = alternativaCorreta;
	}

	public String getTitulo() {
		return titulo;
	}

	public List<Alternativa> getAlternativa() {
		return alternativa;
	}

	public void setAlternativa(List<Alternativa> alternativa) {
		this.alternativa = alternativa;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Long getId() {
		return id;
	}
}