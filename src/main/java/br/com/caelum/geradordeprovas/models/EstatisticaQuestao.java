package br.com.caelum.geradordeprovas.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class EstatisticaQuestao {

	@Id
	@GeneratedValue
	private Long id;

	private long acertos;

	private long erros;

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

	public void incrementaAcertos() {
		acertos++;
	}

	public void incrementaErros() {
		erros++;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
