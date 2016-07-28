package br.com.caelum.geradordeprovas.databuilder;

import java.util.Calendar;
import java.util.List;

import br.com.caelum.geradordeprovas.models.Prova;
import br.com.caelum.geradordeprovas.models.Questao;

public class ProvaBuilder {

	private Long id;

	private String nome;

	private Long duracao;

	private Calendar dataCriacao;

	private Calendar updatedAt;

	private String descricao;

	private List<Questao> questoes;

	public Prova geraProvaPadrao() {
		QuestaoBuilder questaoBuilder = new QuestaoBuilder();
		ProvaBuilder provaBuilder = new ProvaBuilder();
		Calendar hora = Calendar.getInstance();
		provaBuilder.comId(1l).comDescricao("Descricao").comNome("Prova Builder").comDuracao(10l).comDataCriacao(hora)
				.comUpdatedAt(hora).comQuestoes(questaoBuilder.geraListaDeQuestoes());
		return provaBuilder.geraProva();
	}

	private Prova geraProva() {
		Prova prova = new Prova();
		prova.setId(this.id);
		prova.setNome(this.nome);
		prova.setDuracao(this.duracao);
		prova.setDataCriacao(this.dataCriacao);
		prova.setUpdatedAt(this.updatedAt);
		prova.setDescricao(this.descricao);
		prova.setQuestoes(this.questoes);
		return prova;
	}

	public ProvaBuilder comQuestoes(List<Questao> questoes) {
		this.questoes = questoes;
		return this;
	}

	public ProvaBuilder comUpdatedAt(Calendar update) {
		this.updatedAt = update;
		return this;
	}

	public ProvaBuilder comDataCriacao(Calendar dataCriacao) {
		this.dataCriacao = dataCriacao;
		return this;
	}

	public ProvaBuilder comNome(String nome) {
		this.nome = nome;
		return this;
	}

	public ProvaBuilder comId(Long id) {
		this.id = id;
		return this;
	}

	public ProvaBuilder comDuracao(Long duracao) {
		this.duracao = duracao;
		return this;
	}

	public ProvaBuilder comDescricao(String descricao) {
		this.descricao = descricao;
		return this;
	}

	public Prova geraProvaAPartirDeQuestoes(List<Questao> questoes) {
		this.comNome("Prova de Teste").comQuestoes(questoes).comDataCriacao(Calendar.getInstance())
				.comUpdatedAt(Calendar.getInstance()).comDuracao(10l);
		return geraProva();
	}
}
