package br.com.caelum.geradordeprovas.models;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Feedback {

	@Id
	@GeneratedValue
	private Long id;
	
	@OneToOne()
	private Usuario usuario;
	
	@NotEmpty
	@Column(length = 2048)
	String comentario;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataCriacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Calendar getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Calendar dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	public String getDataFormatada() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
		return sdf.format(dataCriacao.getTime());
	}
	
}
