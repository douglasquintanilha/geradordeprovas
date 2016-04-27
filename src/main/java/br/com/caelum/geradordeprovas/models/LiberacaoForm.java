package br.com.caelum.geradordeprovas.models;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.caelum.geradordeprovas.dao.TurmaDao;
import br.com.caelum.geradordeprovas.dao.UsuarioDao;

public class LiberacaoForm {

	@NotEmpty
	private List<Long> provas;

	private List<Long> usuarios = new ArrayList<Long>();

	private List<Turma> turmas = new ArrayList<Turma>();

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

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

	public void liberaProvas(UsuarioDao usuarioDao, TurmaDao turmaDao, List<Prova> provas) {
		liberaProvasUsuarios(usuarioDao, provas);
		liberaProvasTurma(turmaDao, provas);
	}

	private void liberaProvasUsuarios(UsuarioDao usuarioDao, List<Prova> provas) {
		if(!usuarios.isEmpty()){
			for(Long usuarioId : usuarios){
				usuarioDao.salvaProvasLiberadas(usuarioId, provas);
			}
		}
	}

	private void liberaProvasTurma(TurmaDao turmaDao, List<Prova> provas) {
		if(!turmas.isEmpty()){
			for(Turma turma : turmas){
				turmaDao.salvaProvasLiberadas(turma, provas);
			}
		}
	}

}
