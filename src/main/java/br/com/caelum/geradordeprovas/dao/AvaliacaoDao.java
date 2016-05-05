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
	private EntityManager manager;

	public void save(Avaliacao avaliacao) {
		manager.persist(avaliacao);
	}

	public Avaliacao getAvaliacaoPorId(long id) {
		return manager.find(Avaliacao.class, id);
	}

	public Avaliacao getAvaliacao(Long id) {
		return manager.find(Avaliacao.class, id);
	}

	public List<Avaliacao> list() {
		return manager.createQuery("from Avaliacao", Avaliacao.class).getResultList();
	}

	public List<Avaliacao> getAvaliacoesPor(Usuario usuario, Prova prova) {

		List<Avaliacao> avaliacoes = manager
				.createQuery("select a from Avaliacao a where a.usuario.id = :usuarioId AND a.prova.id = :provaId",
						Avaliacao.class).setParameter("usuarioId", usuario.getId())
				.setParameter("provaId", prova.getId()).getResultList();
		return avaliacoes;

	}

	public Avaliacao atualiza(Avaliacao avaliacao) {
		avaliacao = manager.merge(avaliacao);
		return avaliacao;
	}

	public List<Avaliacao> getAvaliacoesDo(Usuario usuario) {
		return manager.createQuery("select a from Avaliacao a where a.usuario.id = :usuarioId", Avaliacao.class)
				.setParameter("usuarioId", usuario.getId()).getResultList();
	}
}
