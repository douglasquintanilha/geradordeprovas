package br.com.caelum.geradordeprovas.models;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class RelatorioUsuario {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Usuario usuario;

	@ElementCollection
	private List<AlternativaMarcada> alternativasMarcadas;

	private Long nota;

	public RelatorioUsuario() {

	}

	public RelatorioUsuario(Usuario usuario, List<AlternativaMarcada> alternativasMarcadas, Long nota) {
		this.usuario = usuario;
		this.nota = nota;
		this.alternativasMarcadas = alternativasMarcadas;
	}

	public Long getNota() {
		return nota;
	}

	public List<AlternativaMarcada> getAlternativasMarcadas() {
		return alternativasMarcadas;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Long getId() {
		return id;
	}

}
