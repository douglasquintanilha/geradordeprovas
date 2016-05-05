package br.com.caelum.geradordeprovas.models;

import java.util.Calendar;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class RelatorioUsuario {

	@Id
	@GeneratedValue
	private int id;

	private Usuario usuario;

	private AlternativaMarcada alternativasMarcadas;
	
	private int nota;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar horarioInicio;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar horarioFim;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

}
