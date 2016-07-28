package br.com.caelum.geradordeprovas.databuilder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.caelum.geradordeprovas.models.AlternativaMarcada;
import br.com.caelum.geradordeprovas.models.Avaliacao;
import br.com.caelum.geradordeprovas.models.Prova;
import br.com.caelum.geradordeprovas.models.Questao;
import br.com.caelum.geradordeprovas.models.RelatorioUsuario;
import br.com.caelum.geradordeprovas.models.Usuario;

public class AvaliacaoBuilder {

	private Long id;

	private int nota;

	private List<Usuario> usuarios;

	private Calendar horarioInicio;

	private Calendar horarioFim;

	private List<AlternativaMarcada> alternativasMarcadas;

	private List<RelatorioUsuario> relatoriosUsuarios = new ArrayList<>();

	private Set<Questao> questoes;

	private String nomeProva;

	private Calendar createdAt;

	private Long duracao;

	private Long provaId;

	public AvaliacaoBuilder comId(Long id) {
		this.id = id;
		return this;
	}

	public AvaliacaoBuilder comNota(int nota) {
		this.nota = nota;
		return this;
	}

	public AvaliacaoBuilder comUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
		return this;
	}

	public AvaliacaoBuilder comHorarioInicio(Calendar horario) {
		this.horarioInicio = horario;
		return this;
	}

	public AvaliacaoBuilder comHorarioFim(Calendar horario) {
		this.horarioFim = horario;
		return this;
	}

	public AvaliacaoBuilder comAlternativasMarcadas(List<AlternativaMarcada> alternativasMarcadas) {
		this.alternativasMarcadas = alternativasMarcadas;
		return this;
	}

	public AvaliacaoBuilder comRelatoriosUsuarios(List<RelatorioUsuario> ru) {
		this.relatoriosUsuarios = ru;
		return this;
	}

	public AvaliacaoBuilder comQuestoes(List<Questao> questoes) {
		this.questoes = new HashSet<Questao>(questoes);
		return this;
	}

	public AvaliacaoBuilder comNomeProva(String nomeProva) {
		this.nomeProva = nomeProva;
		return this;
	}

	public AvaliacaoBuilder comCreatedAt(Calendar createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	public AvaliacaoBuilder comDuracao(Long duracao) {
		this.duracao = duracao;
		return this;
	}

	public AvaliacaoBuilder comProvaId(Long provaId) {
		this.provaId = provaId;
		return this;
	}
	
	private Avaliacao geraAvaliacao(){
		Avaliacao avaliacao = new Avaliacao();
		
		avaliacao.setAlternativasMarcadas(this.alternativasMarcadas);
		avaliacao.setCreatedAt(this.createdAt);
		avaliacao.setHorarioFim(this.horarioFim);
		avaliacao.setHorarioInicio(this.horarioInicio);
		avaliacao.setId(this.id);
		
		return avaliacao;
	}

	public Avaliacao geraAvaliacaoAPartirDaProva(Prova prova) {
		this.comDuracao(prova.getDuracao())
		.comNomeProva(prova.getNome())
		.comCreatedAt(prova.getDataCriacao())
		.comNota(2);
		
		return this.geraAvaliacao();
	}
}
