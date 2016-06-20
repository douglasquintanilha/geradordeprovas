package br.com.caelum.geradordeprovas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.caelum.geradordeprovas.dao.AvaliacaoDao;
import br.com.caelum.geradordeprovas.models.LiberacaoForm;
import br.com.caelum.geradordeprovas.models.Prova;
import br.com.caelum.geradordeprovas.models.Turma;
import br.com.caelum.geradordeprovas.models.Usuario;

@Component
public class LiberacaoService {

	private AvaliacaoDao avaliacaoDao;

	@Autowired
	public LiberacaoService(AvaliacaoDao avaliacaoDao) {
		this.avaliacaoDao = avaliacaoDao;
	}

	public void libera(LiberacaoForm liberacaoForm) {
		liberaProvasEAvaliacoesParaUsuarios(liberacaoForm.getProvas(), liberacaoForm.getUsuarios());
		liberaProvasParaTurmas(liberacaoForm.getProvas(), liberacaoForm.getTurmas());
	}

	private void liberaProvasEAvaliacoesParaUsuarios(List<Prova> provas, List<Usuario> usuarios) {
		for (Usuario usuario : usuarios) {
			usuario.adicionaProvas(provas);
			for (Prova prova : provas) {
				usuario.addAvaliacao(avaliacaoDao.getAvaliacaoMaisRecente(prova));
			}
		}
	}

	private void liberaProvasParaTurmas(List<Prova> provas, List<Turma> turmas) {
		for (Turma turma : turmas) {
			turma.adicionaProvas(provas);
		}
	}

}
