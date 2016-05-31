package br.com.caelum.geradordeprovas.databuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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

	private List<Long> alternativasIds;

	private int nota;

	private Usuario usuario;

	private Prova prova;

	private Calendar horarioInicio;

	private Calendar horarioFim;

	private List<AlternativaMarcada> alternativasMarcadas;

	private List<RelatorioUsuario> relatoriosUsuario = new ArrayList<>();

	private String nomeProva;

	private Calendar createdAt;

	private Set<Questao> questoesImutaveis;

	private Long provaId;

	public Avaliacao geraAvaliacaoSemRelatorioUsuario() {
		AvaliacaoBuilder avaliacaoBuilder = new AvaliacaoBuilder();

		Calendar inicio = Calendar.getInstance();
		inicio.set(2016, 04, 04, 12, 00);
		Calendar fim = Calendar.getInstance();
		fim.set(2016, 04, 04, 12, 05);

		ProvaBuilder provaBuilder = new ProvaBuilder();
		Prova prova = provaBuilder.geraProvaPadrao();
		Calendar created = prova.getUpdatedAt();
		created.add(Calendar.DAY_OF_YEAR, 1);

		QuestaoBuilder questaoBuilder = new QuestaoBuilder();
		Set<Questao> questoes = new HashSet<>(questaoBuilder.geraListaDeQuestoes());

		avaliacaoBuilder.comAlternativasIds(Arrays.asList(1l, 2l))
				.comAlternativasMarcadas(geraDuasAlternativasMarcadasCorretas()).comCreatedAt(created)
				.comHorarioInicio(inicio).comHorarioFim(fim).comId(1l).comNomeProva(prova.getNome()).comNota(2)
				.comProva(prova).comProvaId(prova.getId()).comQuestoesImutaveis(questoes)
				.comUsuario(geraUsuarioComum(prova));
		return avaliacaoBuilder.geraAvaliacao();
	}

	private Usuario geraUsuarioComum(Prova prova) {
		Usuario usuario = new Usuario();
		usuario.setAdmin(false);
		usuario.setId(1l);
		usuario.setLogin("login");
		usuario.setSenha("senha");
		usuario.setProvas(Arrays.asList(prova));
		return usuario;
	}

	private List<AlternativaMarcada> geraDuasAlternativasMarcadasCorretas() {
		AlternativaMarcada am1 = new AlternativaMarcada();
		am1.setId(1l);
		am1.setDescricao("alternativa marcada builder 1");
		am1.setAlternativaCorreta(true);
		AlternativaMarcada am2 = new AlternativaMarcada();
		am2.setId(2l);
		am1.setDescricao("alternativa marcada builder 2");
		am1.setAlternativaCorreta(true);
		List<AlternativaMarcada> alternativasMarcadas = Arrays.asList(am1, am2);
		return alternativasMarcadas;
	}

	private Avaliacao geraAvaliacao() {
		Avaliacao avaliacao = new Avaliacao();
		avaliacao.setId(this.id);
		avaliacao.setAlternativasIds(this.alternativasIds);
		avaliacao.setNota(this.nota);
		avaliacao.setNomeProva(this.nomeProva);
		avaliacao.setCreatedAt(this.createdAt);
		avaliacao.setHorarioFim(this.horarioFim);
		avaliacao.setHorarioInicio(this.horarioInicio);
		avaliacao.setAlternativasMarcadas(this.alternativasMarcadas);
		avaliacao.setProvaId(this.provaId);
		avaliacao.setQuestoesImutaveis(this.questoesImutaveis);
		avaliacao.setUsuario(this.usuario);
		avaliacao.setRelatorioUsuarios(this.relatoriosUsuario);
		return avaliacao;
	}

	public AvaliacaoBuilder comProvaId(Long provaId) {
		this.provaId = provaId;
		return this;
	}

	public AvaliacaoBuilder comQuestoesImutaveis(Set<Questao> questoes) {
		this.questoesImutaveis = questoes;
		return this;
	}

	public AvaliacaoBuilder comCreatedAt(Calendar createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	public AvaliacaoBuilder comNomeProva(String nomeProva) {
		this.nomeProva = nomeProva;
		return this;
	}

	public AvaliacaoBuilder comRelatorioUsuario(List<RelatorioUsuario> ru) {
		this.relatoriosUsuario = ru;
		return this;
	}

	public AvaliacaoBuilder comAlternativasMarcadas(List<AlternativaMarcada> alternativasMarcadas) {
		this.alternativasMarcadas = alternativasMarcadas;
		return this;
	}

	public AvaliacaoBuilder comHorarioFim(Calendar horario) {
		this.horarioFim = horario;
		return this;
	}

	public AvaliacaoBuilder comHorarioInicio(Calendar horario) {
		this.horarioInicio = horario;
		return this;
	}

	public AvaliacaoBuilder comProva(Prova prova) {
		this.prova = prova;
		return this;
	}

	public AvaliacaoBuilder comUsuario(Usuario usuario) {
		this.usuario = usuario;
		return this;
	}

	public AvaliacaoBuilder comNota(int nota) {
		this.nota = nota;
		return this;
	}

	public AvaliacaoBuilder comAlternativasIds(List<Long> ids) {
		this.alternativasIds = ids;
		return this;
	}

	public AvaliacaoBuilder comId(Long id) {
		this.id = id;
		return this;
	}

}
