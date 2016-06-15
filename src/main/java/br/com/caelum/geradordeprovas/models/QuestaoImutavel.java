package br.com.caelum.geradordeprovas.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

import br.com.caelum.geradordeprovas.validation.Size;

@Entity
public class QuestaoImutavel {

	@Id
	private Long id;

	@Column(length = 2048)
	@NotBlank()
	private String titulo;

	@ManyToMany(cascade = { CascadeType.PERSIST })
	@JoinColumn(unique = true)
	private Set<Tag> tags = new HashSet<Tag>();

	@Size(5)
	@Valid
	@OneToMany(cascade = { CascadeType.PERSIST })
	private List<Alternativa> alternativa;

	@NotBlank
	@Transient
	private String alternativaCorreta;

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

	public QuestaoImutavel geraQuestaoImutavelAPartirDe(Questao questao) {
		this.setAlternativa(questao.getAlternativa());
		this.setAlternativaCorreta(questao.getAlternativaCorreta());
		this.setId(questao.getId());
		this.setTags(questao.getTags());
		this.setTitulo(questao.getTitulo());
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alternativa == null) ? 0 : alternativa.hashCode());
		result = prime * result + ((alternativaCorreta == null) ? 0 : alternativaCorreta.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
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
		QuestaoImutavel other = (QuestaoImutavel) obj;
		if (alternativa == null) {
			if (other.alternativa != null)
				return false;
		} else if (!alternativa.equals(other.alternativa))
			return false;
		if (alternativaCorreta == null) {
			if (other.alternativaCorreta != null)
				return false;
		} else if (!alternativaCorreta.equals(other.alternativaCorreta))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (!tags.equals(other.tags))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}

	
}
