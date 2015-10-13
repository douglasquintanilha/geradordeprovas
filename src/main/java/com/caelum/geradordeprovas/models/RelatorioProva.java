package com.caelum.geradordeprovas.models;

import java.util.List;

public class RelatorioProva {

	long nota;

	long totalDeQuestoes;

	boolean temErrada = false;

	List<Questao> questoes;

	List<Boolean> resultado;

	public RelatorioProva(long nota, long totalDeQuestoes, boolean temErrada,
			List<Questao> questoes, List<Boolean> resultado) {

		this.nota = nota;
		this.totalDeQuestoes = totalDeQuestoes;
		this.temErrada = temErrada;
		this.questoes = questoes;
		this.resultado = resultado;

	}

	public long getNota() {
		return nota;
	}

	public long getTotalDeQuestoes() {
		return totalDeQuestoes;
	}

	public boolean isTemErrada() {
		return temErrada;
	}

	public List<Questao> getQuestoes() {
		return questoes;
	}

	public List<Boolean> getResultado() {
		return resultado;
	}

}
