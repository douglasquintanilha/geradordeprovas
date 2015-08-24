package com.caelum.geradordeprovas.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Questao {

	@Id
	@GeneratedValue
	private Long Id;
	
	@NotBlank
	private String titulo;
	
	@Transient
	private List<String> alternativa;

	@Transient
	private String alternativaCorreta;
	

	public List<String> getAlternativa() {
		return alternativa;
	}

	public void setAlternativa(List<String> alternativa) {
		this.alternativa = alternativa;
	}

	public String getAlternativaCorreta() {
		return alternativaCorreta;
	}

	public void setAlternativaCorreta(String alternativaCorreta) {
		this.alternativaCorreta = alternativaCorreta;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Long getId() {
		return Id;
	}
}

