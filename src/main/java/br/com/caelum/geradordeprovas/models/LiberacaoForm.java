package br.com.caelum.geradordeprovas.models;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.caelum.geradordeprovas.dao.AvaliacaoDao;
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

	public void liberaProvas(AvaliacaoDao avaliacaoDao, UsuarioDao usuarioDao, TurmaDao turmaDao, List<Prova> provas) {
		liberaProvasUsuarios(usuarioDao, provas);
		liberaProvasTurma(turmaDao, provas);
		List<Avaliacao> avaliacoes = geraAvaliacoesDasProvas(avaliacaoDao, provas);
		liberaAvaliacoesUsuarios(usuarioDao, avaliacoes);
		liberaAvaliacoesTurmas(turmaDao, avaliacoes);
	}

	private void liberaAvaliacoesTurmas(TurmaDao turmaDao, List<Avaliacao> avaliacoes) {
		if(!turmas.isEmpty()){
			for(Turma turma : turmas){
				turmaDao.salvaAvaliacoesLiberadas(turma, avaliacoes);
			}
		}
	}

	private List<Avaliacao> geraAvaliacoesDasProvas(AvaliacaoDao avaliacaoDao, List<Prova> provas) {
		//assim, a cada liberacao, ta gerando uma nova avaliacao.
		//tem que verificar se a prova ja tem uma avaliacao gerada,
		//se tiver, tem q ver se essa avaliacao é a mais atualizada possivel(HOW??)
		List<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();
		for(Prova prova : provas){
			avaliacoes.add(prova.geraAvaliacaoInicial());
		}
		avaliacaoDao.salvaAvaliacoes(avaliacoes);
		return avaliacoes;
	}

	private void liberaProvasUsuarios(UsuarioDao usuarioDao, List<Prova> provas) {
		if(!usuarios.isEmpty()){
			for(Long usuarioId : usuarios){
				usuarioDao.salvaProvasLiberadas(usuarioId, provas);
			}
		}
	}

	private void liberaAvaliacoesUsuarios(UsuarioDao usuarioDao, List<Avaliacao> avaliacoes) {
		if(!usuarios.isEmpty()){
			for(Long usuarioId : usuarios){
				usuarioDao.salvaAvaliacoesLiberadas(usuarioId, avaliacoes);
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
