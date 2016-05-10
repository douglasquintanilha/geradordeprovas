package br.com.caelum.geradordeprovas.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.caelum.geradordeprovas.dao.ProvaDao;
import br.com.caelum.geradordeprovas.dao.QuestaoDao;
import br.com.caelum.geradordeprovas.dao.TagDao;
import br.com.caelum.geradordeprovas.models.Alternativa;
import br.com.caelum.geradordeprovas.models.Prova;
import br.com.caelum.geradordeprovas.models.Questao;
import br.com.caelum.geradordeprovas.models.Tag;

@Component
public class QuestaoService {

	private QuestaoDao questaoDao;
	private TagDao tagDao;
	private ProvaDao provaDao;

	@Autowired
	public QuestaoService(ProvaDao provaDao, QuestaoDao questaoDao, TagDao tagDao) {
		this.questaoDao = questaoDao;
		this.tagDao = tagDao;
		this.provaDao = provaDao;
	}

	public void verificaSeTagJaExisteNoBanco(Questao questao) {
		Set<Tag> outraListaDeTag = new HashSet<>();

		for (Tag tag : questao.getTags()) {
			try {
				String nome = tag.getNome();
				Tag tagBanco = tagDao.getTagPorNome(nome);
				seTagNaoTiverNoBanco(outraListaDeTag, tagBanco);
			} catch (NoResultException e) {
				tagDao.save(tag);
				outraListaDeTag.add(tag);
			}
		}
		questao.setTags(outraListaDeTag);
	}

	private void seTagNaoTiverNoBanco(Set<Tag> outraListaDeTag, Tag tagBanco) {
		if (tagBanco != null) {
			outraListaDeTag.add(tagBanco);
		}
	}

	public void preencheAlternativaCorreta(Questao questao) {
		int alternativaCorreta = Integer.parseInt(questao.getAlternativaCorreta());
		List<Alternativa> alternativas = questao.getAlternativa();
		for (int i = 0; i < alternativas.size(); i++) {
			if (i == alternativaCorreta) {
				alternativas.get(i).setAlternativaCorreta(true);
			}
		}
	}

	public void salva(Questao questao) {
		verificaSeTagJaExisteNoBanco(questao);
		questaoDao.save(questao);
		preencheAlternativaCorreta(questao);
	}

	public void edita(Questao questao) {
		verificaSeTagJaExisteNoBanco(questao);
		preencheAlternativaCorreta(questao);
		atualizaUpdatedAtDeProvas(provaDao.getProvasPor(questao));
	}

	private void atualizaUpdatedAtDeProvas(List<Prova> provas) {
		System.out.println(provas);
		for (Prova prova : provas) {
			prova.setUpdatedAt();
		}
	}

}
