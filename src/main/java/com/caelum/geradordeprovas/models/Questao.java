package com.caelum.geradordeprovas.models;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Questao {

	@Id
	@GeneratedValue
	private Long id;
	
	// (DONE) transformar em @Collumn 
	@Column(length=2048)
	@NotBlank()
	private String titulo;
	
	@ManyToMany(cascade = {CascadeType.PERSIST})
	@JoinColumn(unique=true)
	private Set<Tag> tags = new HashSet<Tag>();
	
	@Valid
	@OneToMany(cascade={CascadeType.PERSIST})
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

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
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
	
	public void setId(Long id) {
		this.id = id;
	}

}
