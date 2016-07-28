package br.com.caelum.geradordeprovas.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.caelum.geradordeprovas.dao.AvaliacaoDao;
import br.com.caelum.geradordeprovas.dao.ProvaDao;
import br.com.caelum.geradordeprovas.dao.QuestaoDao;
import br.com.caelum.geradordeprovas.dao.UsuarioDao;
import br.com.caelum.geradordeprovas.databuilder.ProvaBuilder;
import br.com.caelum.geradordeprovas.databuilder.QuestaoBuilder;
import br.com.caelum.geradordeprovas.databuilder.UsuarioBuilder;
import br.com.caelum.geradordeprovas.models.Prova;
import br.com.caelum.geradordeprovas.models.Questao;
import br.com.caelum.geradordeprovas.models.Usuario;

@Component
public class PopulaBancoService {

	private QuestaoDao questaoDao;
	private ProvaDao provaDao;
	private UsuarioDao usuarioDao;

	@Autowired
	public PopulaBancoService(UsuarioDao usuarioDao, QuestaoDao questaoDao, ProvaDao provaDao, AvaliacaoDao avaliacaoDao) {
		this.questaoDao = questaoDao;
		this.provaDao = provaDao;
		this.usuarioDao = usuarioDao;
	}

	public void popula() {
		List<Questao> questoes = persistQuestao();
		persistProva(questoes);
		persistUsuarios();
	}

	private void persistUsuarios() {
		UsuarioBuilder builder = new UsuarioBuilder();
		Usuario comum = builder.comLogin("usuario@teste.com.br").comSenha("12345").comAdmin(false).geraUsuarioSemId();
		Usuario admin = builder.comLogin("admin@teste.com.br").comSenha("12345").comAdmin(true).geraUsuarioSemId();
		usuarioDao.save(comum);
		usuarioDao.save(admin);
	}

	private Prova persistProva(List<Questao> questoes) {
		ProvaBuilder builder = new ProvaBuilder();
		List<Questao> questoesMerged = new ArrayList<>();
		for(Questao questao : questoes){
			questoesMerged.add(questaoDao.merge(questao));
		}
		Prova prova = builder.geraProvaAPartirDeQuestoes(questoesMerged);
		provaDao.save(prova);
		return prova;
	}

	public List<Questao> persistQuestao() {
		QuestaoBuilder builder = new QuestaoBuilder();
		List<Questao> questoes = builder.geraListaDeQuestoesSemId();
		questaoDao.save(questoes);
		return questoes;
	}

}
