package br.com.caelum.geradordeprovas.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class Avaliacao {

	@Id
	@GeneratedValue
	private Long id;

	@Transient
	private List<Long> alternativasIds;

	private int nota;

	@ManyToMany
	private List<Usuario> usuarios;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar horarioInicio;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar horarioFim;

	@ElementCollection
	private List<AlternativaMarcada> alternativasMarcadas;

	// -------------------------Atributos do refactor

	@Transient
	private List<RelatorioUsuario> relatorioUsuarios = new ArrayList<>();

	private String nomeProva;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar createdAt;

	private Long duracao;

	// @ElementCollection
	// private Set<QuestaoImutavel> questoesImutaveis;

	private Long provaId;

	@ManyToOne
	private Prova prova;

	public Avaliacao() {

	}

	public Avaliacao(String nomeProva, Set<QuestaoImutavel> questoesImutaveis, Long provaId) {
		this.nomeProva = nomeProva;
		// this.questoesImutaveis = questoesImutaveis;
		this.provaId = provaId;
		this.createdAt = Calendar.getInstance();
	}
	
	public void setProvaId(Long provaId) {
		this.provaId = provaId;
	}

	public Long getProvaId() {
		return provaId;
	}

	public Calendar getCreatedAt() {
		return createdAt;
	}

	public List<AlternativaMarcada> getAlternativasMarcadas() {
		return alternativasMarcadas;
	}

	public void setAlternativasMarcadas(List<AlternativaMarcada> alternativasMarcadas) {
		this.alternativasMarcadas = alternativasMarcadas;
	}

	public Calendar getHorarioInicio() {
		return horarioInicio;
	}

	public void setHorarioInicio(Calendar horaInicio) {
		this.horarioInicio = horaInicio;
	}

	public Calendar getHorarioFim() {
		return horarioFim;
	}

	public void setHorarioFim(Calendar horaFim) {
		this.horarioFim = horaFim;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeProva() {
		return nomeProva;
	}

	public void setNomeProva(String nomeProva) {
		this.nomeProva = nomeProva;
	}

	// public Set<QuestaoImutavel> getQuestoesImutaveis() {
	// return questoesImutaveis;
	// }
	//
	// public void setQuestoesImutaveis(Set<QuestaoImutavel> questoesImutaveis)
	// {
	// this.questoesImutaveis = questoesImutaveis;
	// }

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public List<Usuario> getUsuario() {
		return usuarios;
	}

	public void setUsuario(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Long> getAlternativasIds() {
		return alternativasIds;
	}

	public void setAlternativasIds(List<Long> alternativasIds) {
		this.alternativasIds = alternativasIds;
	}

	// public void corrige() {
	// this.nota = 0;
	// if (alternativasMarcadas == null) {
	// return;
	// }
	//
	// int i = 0;
	//
	// for (AlternativaMarcada alternativaMarcada : alternativasMarcadas) {
	// if (alternativaMarcada.isAlternativaCorreta()) {
	// this.nota++;
	// prova.getQuestoes().get(i).atualizaEstatistica(true, usuario);
	// } else {
	// prova.getQuestoes().get(i).atualizaEstatistica(false, usuario);
	// }
	// i++;
	// }
	//
	// }
	//
	// public boolean validaDuracao() {
	// long duracao = this.horarioFim.getTimeInMillis() -
	// this.horarioInicio.getTimeInMillis();
	// long duracaoComTolerancia = this.getProva().getDuracao() + 1;
	// long duracaoComToleranciaEmMilis = duracaoComTolerancia * 60 * 1000;
	// if (duracaoComToleranciaEmMilis >= duracao)
	// return true;
	// else
	// return false;
	// }

	private Prova getProva() {
		return this.prova;
	}

	public long getDuracao() {
		long duracao = this.horarioFim.getTimeInMillis() - this.horarioInicio.getTimeInMillis();
		return (duracao / 60) / 1000;
	}

	public String getDataRealizada() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
		return sdf.format(this.horarioInicio.getTime());
	}

	public String getUuid() {
		return UUID.randomUUID().toString();
	}

	public void setCreatedAt(Calendar horario) {
		this.createdAt = horario;
	}

	public void setRelatorioUsuarios(List<RelatorioUsuario> relatorioUsuarios) {
		this.relatorioUsuarios = relatorioUsuarios;
	}

}