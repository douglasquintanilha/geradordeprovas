package br.com.caelum.geradordeprovas.models;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

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

	// Mudar a List para Set , e mandar mensagem pro properties
	@NotEmpty()
	@ManyToMany()
	private List<Questao> questoes;

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Questao> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(List<Questao> questoes) {
		this.questoes = questoes;
	}

	public int getQuantidadeDeQuestoes() {
		return questoes.size();
	}

	public long getDuracao() {
		return duracao;
	}

	public void setDuracao(long duracao) {
		this.duracao = duracao;
	}

	public Prova embaralha(){
		this.embaralhaQuestoes();
		this.embaralhaAlternativas();
		return this;
	}

	public void embaralhaAlternativas(){
		for(Questao questao : this.questoes){
			Collections.shuffle(questao.getAlternativa());
		}
	}
	
	public void embaralhaQuestoes(){
		Collections.shuffle(this.questoes);
	}
	
	public String getUuid(){
		return UUID.randomUUID().toString();
	}
}