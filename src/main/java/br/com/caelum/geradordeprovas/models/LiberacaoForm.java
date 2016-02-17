package br.com.caelum.geradordeprovas.models;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;


public class LiberacaoForm {
	
	@NotEmpty
	private List<Long> provas;
	
	@NotEmpty
	private List<Long> usuarios;
	

	public List<Long> getProvas() {
		return provas;
	}
	public void setProvas(List<Long> provas) {
		this.provas = provas;
	}
	public List<Long> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Long> usuarios) {
		this.usuarios = usuarios;
	}


}
