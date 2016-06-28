package br.com.caelum.geradordeprovas.models;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Prova {

	@Id
	@GeneratedValue
	private Long id;

	@NotBlank
	private String nome;

	private Long duracao;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataCriacao;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar updatedAt;

	@Column(length = 2048)
	private String descricao;

	@NotEmpty()
	@ManyToMany()
	private List<Questao> questoes;

	public void setUpdatedAt(Calendar horario) {
		this.updatedAt = horario;
	}

	@PreUpdate
	@PrePersist
	public void atualizaUpdateAt() {
		this.updatedAt = Calendar.getInstance();
	}

	public Calendar getUpdatedAt() {
		return updatedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getDuracao() {
		return duracao;
	}

	public void setDuracao(Long duracao) {
		this.duracao = duracao;
	}

	public Calendar getDataCriacao() {
		return dataCriacao;
	}

	public String getDataFormatada() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
		return sdf.format(dataCriacao.getTime());
	}

	public void setDataCriacao(Calendar dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Questao> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(List<Questao> questoes) {
		this.questoes = questoes;
	}

	public Prova embaralha() {
		this.embaralhaQuestoes();
		this.embaralhaAlternativas();
		return this;
	}

	public void embaralhaAlternativas() {
		for (Questao questao : this.questoes) {
			Collections.shuffle(questao.getAlternativa());
		}
	}

	public void embaralhaQuestoes() {
		Collections.shuffle(this.questoes);
	}

	public String getUuid() {
		return UUID.randomUUID().toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prova other = (Prova) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Avaliacao geraAvaliacaoInicial() {
		return new Avaliacao(this.getNome(), new HashSet<Questao>(questoes), this.id, duracao);
	}

}