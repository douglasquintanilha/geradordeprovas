package br.com.caelum.geradordeprovas.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

import br.com.caelum.geradordeprovas.validation.Size;

@Entity
public class Questao {

	@Id
	@GeneratedValue
	private Long id;

	@Column(length = 2048)
	@NotBlank()
	private String titulo;

	@ManyToMany(cascade = { CascadeType.PERSIST })
	@JoinColumn(unique = true)
	private Set<Tag> tags = new HashSet<Tag>();

	@OneToOne(cascade = { CascadeType.PERSIST })
	private EstatisticaQuestao estatistica = new EstatisticaQuestao();

	@Size(5)
	@Valid
	@OneToMany(cascade = { CascadeType.PERSIST })
	private List<Alternativa> alternativa;

	@NotBlank
	@Transient
	private String alternativaCorreta;

	public void atualizaEstatistica(boolean acertou, Usuario usuario) {
		if (usuario.isAdmin()) {
			return;
		}
		if (acertou)
			estatistica.incrementaAcertos();
		else
			estatistica.incrementaErros();

	}

	public String getAlternativaCorreta() {
		return alternativaCorreta;
	}

	public void setAlternativaCorreta(String alternativaCorreta) {
		this.alternativaCorreta = alternativaCorreta;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public String getTitulo() {
		return titulo;
	}

	public List<Alternativa> getAlternativa() {
		return alternativa;
	}

	public void setAlternativa(List<Alternativa> alternativa) {
		this.alternativa = alternativa;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EstatisticaQuestao getEstatistica() {
		return estatistica;
	}

	public void setEstatistica(EstatisticaQuestao estatistica) {
		this.estatistica = estatistica;
	}

	public void edita(Questao questaoEditada) {

		this.alternativa.clear();
		this.alternativa.addAll(questaoEditada.getAlternativa());

		this.alternativaCorreta = questaoEditada.getAlternativaCorreta();

		this.tags.addAll(questaoEditada.getTags());

		this.titulo = questaoEditada.getTitulo();

	}
	
	//Wesley
	public boolean corrige(AlternativaMarcada alternativaMarcada){
		for (Alternativa alter : alternativa) {
			if(alter.isAlternativaCorreta()){
				if(alter.getId() == alternativaMarcada.getId()){
					return true;
				}else{
					return false;
				}
			}
		}
		return false;
	}

}