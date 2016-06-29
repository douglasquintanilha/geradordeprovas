package br.com.caelum.geradordeprovas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.caelum.geradordeprovas.models.Avaliacao;
import br.com.caelum.geradordeprovas.models.Prova;
import br.com.caelum.geradordeprovas.models.Usuario;

@Repository
public class AvaliacaoDao {

	@PersistenceContext
	private EntityManager em;

	public void save(Avaliacao avaliacao) {
		em.persist(avaliacao);
	}

	public void save(List<Avaliacao> avaliacoes) {
		for (Avaliacao avaliacao : avaliacoes) {
			em.persist(avaliacao);
		}
	}

	public Avaliacao getAvaliacao(Avaliacao avaliacao) {
		return em.find(Avaliacao.class, avaliacao.getId());
	}

	public Avaliacao getAvaliacao(Long id) {
		return em.find(Avaliacao.class, id);
	}

	public List<Avaliacao> list() {
		return em.createQuery("from Avaliacao", Avaliacao.class).getResultList();
	}

	public List<Avaliacao> findBy(Usuario usuario, Prova prova) {

		List<Avaliacao> avaliacoes = em
				.createQuery("select a from Avaliacao a where a.usuario.id = :usuarioId AND a.prova.id = :provaId",
						Avaliacao.class).setParameter("usuarioId", usuario.getId())
				.setParameter("provaId", prova.getId()).getResultList();
		return avaliacoes;

	}

	public Avaliacao merge(Avaliacao avaliacao) {
		Avaliacao av = em.merge(avaliacao);
		return av;
	}

	public List<Avaliacao> findBy(Usuario usuario) {
		return em.createQuery("select a from Avaliacao a where a.usuario.id = :usuarioId", Avaliacao.class)
				.setParameter("usuarioId", usuario.getId()).getResultList();
	}

	public Avaliacao getAvaliacaoMaisRecente(Prova prova) {
		List<Avaliacao> avaliacoes = em
				.createQuery("select a from Avaliacao a where a.provaId = :provaId", Avaliacao.class)
				.setParameter("provaId", prova.getId()).getResultList();
		if (avaliacoes.size() == 0)
			return new Avaliacao();
		else
			return avaliacoes.get(avaliacoes.size() - 1);
	}

}