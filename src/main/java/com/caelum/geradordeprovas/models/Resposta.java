package com.caelum.geradordeprovas.models;

import java.util.List;

import javax.persistence.Transient;

public class Resposta {

	@Transient
	private List<Long> alternativas;

	public List<Long> getAlternativas() {
		return alternativas;
	}

	public void setAlternativas(List<Long> alternativas) {
		this.alternativas = alternativas;
	}

	public boolean validacao(Prova prova) {

		if (prova.getQuestoes().size() > this.getAlternativas().size())
			return false;
		else
			return true;
		
	}

}
