package br.com.caelum.geradordeprovas.models;

import java.util.Calendar;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class RelatorioUsuario {

	@Id
	@GeneratedValue
	private Long id;

//	@OneToOne
//	@JoinColumn(unique=true)
//	private Usuario usuario;

	@ElementCollection
	private List<AlternativaMarcada> alternativasMarcadas;
	
	private int nota;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar horarioInicio;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar horarioFim;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public Usuario getUsuario() {
//		return usuario;
//	}
//
//	public void setUsuario(Usuario usuario) {
//		this.usuario = usuario;
//	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

}
