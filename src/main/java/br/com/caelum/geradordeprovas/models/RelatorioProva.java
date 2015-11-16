package br.com.caelum.geradordeprovas.models;

import java.util.List;

public class RelatorioProva {

	long nota;

	boolean temErrada = false;

	List<Questao> questoes;
	
	List<Alternativa> alternativasMarcadas;

	long qntDeQuestoes;
	
	List<Boolean> resultado;

	public RelatorioProva(long nota, boolean temErrada,
			List<Questao> questoes, List<Boolean> resultado) {

		this.nota = nota;
		this.temErrada = temErrada;
		this.questoes = questoes;
		this.resultado = resultado;

	}

	public long getNota() {
		return nota;
	}
	
	public long getQntDeQuestoes(){
		this.qntDeQuestoes = this.questoes.size();
		return qntDeQuestoes;
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
