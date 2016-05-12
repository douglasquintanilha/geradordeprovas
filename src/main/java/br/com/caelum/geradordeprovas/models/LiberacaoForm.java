package br.com.caelum.geradordeprovas.models;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

public class LiberacaoForm {

	@NotEmpty
	private List<Prova> provas;

	private List<Usuario> usuarios = new ArrayList<>();

	private List<Turma> turmas = new ArrayList<>();

	public void setProvas(List<Prova> provas) {
		this.provas = provas;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public List<Prova> getProvas() {
		return provas;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

}
