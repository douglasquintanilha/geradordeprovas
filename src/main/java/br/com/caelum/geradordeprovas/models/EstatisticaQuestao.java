package br.com.caelum.geradordeprovas.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class EstatisticaQuestao {

	@Id
	@GeneratedValue
	private Long id;
	
	@OneToOne()
	private Questao questao;
	
	private long acertos;
	
	private long erros;

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}

	public long getAcertos() {
		return acertos;
	}

	public void setAcertos(long acertos) {
		this.acertos = acertos;
	}

	public long getErros() {
		return erros;
	}

	public void setErros(long erros) {
		this.erros = erros;
	}
	
}
