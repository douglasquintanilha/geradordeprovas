package br.com.caelum.geradordeprovas.models;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class Avaliacao {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Transient
	private List<Long> alternativasIds;

	private int nota;
	
	@OneToOne()
	private Usuario usuario;
	
	@OneToOne()
	private Prova prova;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataRealizada;
	
	@ElementCollection
	private List<AlternativaMarcada> alternativasMarcadas;

	public List<AlternativaMarcada> getAlternativasMarcadas() {
		return alternativasMarcadas;
	}

	public void setAlternativasMarcadas(
			List<AlternativaMarcada> alternativasMarcadas) {
		this.alternativasMarcadas = alternativasMarcadas;
	}

	public Calendar getDataRealizada() {
		return dataRealizada;
	}

	public void setDataRealizada(Calendar dataRealizada) {
		this.dataRealizada = dataRealizada;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Prova getProva() {
		return prova;
	}

	public void setProva(Prova prova) {
		this.prova = prova;
	}

	public void corrige() {
		this.nota = 0;
		for (AlternativaMarcada alternativaMarcada : alternativasMarcadas) {
			if(alternativaMarcada.isAlternativaCorreta()){
				this.nota++;
			}
		}
		
	}
	
	public List<Long> getAlternativasIds() {
		return alternativasIds;
	}

	public void setAlternativasIds(List<Long> alternativasIds) {
		this.alternativasIds = alternativasIds;
	}
	
	
}