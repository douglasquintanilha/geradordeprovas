package com.caelum.geradordeprovas.models;

import java.util.ArrayList;
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

	// Mudar a List para Set , e mandar mensagem pro properties
	@NotEmpty(message = "A prova deve conter pelo menos uma questão!")
	@ManyToMany()
	private List<Questao> questoes;

	public Long getId() {
		return id;
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

	public RelatorioProva corrige(List<Alternativa> marcadas) {

		List<Alternativa> acertou = new ArrayList<>();
		List<Boolean> resultado = new ArrayList<>();
		boolean temErrada = false;
		List<Questao> questoes = this.getQuestoes();

		for (Alternativa alternativa : marcadas) {
			if (alternativa.isAlternativaCorreta()) {
				acertou.add(alternativa);
				resultado.add(true);
			} else {
				resultado.add(false);
				temErrada = true;
			}
		}

		RelatorioProva rp = new RelatorioProva(acertou.size(), marcadas.size(),
				temErrada, questoes, resultado);

		return rp;
	}

}