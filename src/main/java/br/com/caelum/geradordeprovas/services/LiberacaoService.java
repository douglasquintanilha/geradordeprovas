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
	public LiberacaoService(AvaliacaoDao avaliacaoDao) {
		this.avaliacaoDao = avaliacaoDao;
	}

	public void libera(LiberacaoForm liberacaoForm) {
		List<Avaliacao> avaliacoes = geraAvaliacoes(liberacaoForm.getProvas());
		liberaAvaliacoesParaUsuarios(avaliacoes, liberacaoForm.getUsuarios());
		liberaAvaliacoesParaTurmas(avaliacoes, liberacaoForm.getTurmas());
	}

	public void liberaAvaliacoesParaTurmas(List<Avaliacao> avaliacoes, List<Turma> turmas) {
		for (Turma turma : turmas) {
			turma.adicionaAvaliacoes(avaliacoes);
		}
	}

	public void liberaAvaliacoesParaUsuarios(List<Avaliacao> avaliacoes, List<Usuario> usuarios) {
		for (Usuario usuario : usuarios) {
			usuario.adicionaAvaliacoes(avaliacoes);
		}
	}
	
	public List<Avaliacao> geraAvaliacoes(List<Prova> provas) {
		List<Avaliacao> avaliacoesASeremLiberadas = new ArrayList<>();
		for (Prova prova : provas) {
			Avaliacao avaliacao = avaliacaoDao.getUltimaAvaliacaoCriada(prova);
			if (provaFoiAtualizadaDepoisDaUltimaAvaliacao(avaliacao, prova)) {
				avaliacoesASeremLiberadas.add(prova.geraAvaliacaoInicial());
			} else {
				avaliacoesASeremLiberadas.add(avaliacao);
			}
		}
		System.out.println(avaliacoesASeremLiberadas.get(0).getNomeProva()+"ahahhhh");
		avaliacaoDao.save(avaliacoesASeremLiberadas);
		return avaliacoesASeremLiberadas;
	}

	public boolean provaFoiAtualizadaDepoisDaUltimaAvaliacao(Avaliacao avaliacao, Prova prova) {
		if(avaliacao.getCreatedAt() == null){
			return true;
		}
		return prova.getUpdatedAt().after(avaliacao.getCreatedAt());
	}

}
