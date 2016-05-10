package br.com.caelum.geradordeprovas.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.caelum.geradordeprovas.dao.AvaliacaoDao;
import br.com.caelum.geradordeprovas.models.Avaliacao;
import br.com.caelum.geradordeprovas.models.LiberacaoForm;
import br.com.caelum.geradordeprovas.models.Prova;
import br.com.caelum.geradordeprovas.models.Turma;
import br.com.caelum.geradordeprovas.models.Usuario;

@Component
public class LiberacaoService {

	private AvaliacaoDao avaliacaoDao;
	
	@Autowired
	public LiberacaoService(AvaliacaoDao avaliacaoDao){
		this.avaliacaoDao = avaliacaoDao;
	}
	
	public void libera(LiberacaoForm liberacaoForm) {
		List<Avaliacao> avaliacoes = geraAvaliacoes(liberacaoForm.getProvas());
		liberaAvaliacoes(avaliacoes, liberacaoForm.getTurmas(), liberacaoForm.getUsuarios());
	}

	private void liberaAvaliacoes(List<Avaliacao> avaliacoes, List<Turma> turmas, List<Usuario> usuarios) {
		for(Usuario usuario : usuarios){
			usuario.adicionaAvaliacoes(avaliacoes);
		}
		for(Turma turma : turmas){
			turma.adicionaAvaliacoes(avaliacoes);
		}
	}

	private List<Avaliacao> geraAvaliacoes(List<Prova> provas) {
		List<Avaliacao> avaliacoes = new ArrayList<>();
		for (Prova prova : provas) {
			avaliacoes.add(prova.geraAvaliacaoInicial());
		}
		avaliacaoDao.save(avaliacoes);
		return avaliacoes;
	}

}
