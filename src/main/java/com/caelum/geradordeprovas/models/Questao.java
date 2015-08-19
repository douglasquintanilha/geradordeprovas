package com.caelum.geradordeprovas.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Questao {

	@Id
	@GeneratedValue
	private Long Id;
	
	@NotEmpty
	private String titulo;

	@NotEmpty
	private String alternativaCorreta;
}

