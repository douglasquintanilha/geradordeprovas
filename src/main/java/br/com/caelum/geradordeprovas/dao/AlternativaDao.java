package br.com.caelum.geradordeprovas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.caelum.geradordeprovas.models.Alternativa;

@Repository
public class AlternativaDao {

	@PersistenceContext
	private EntityManager em;

	public void save(Alternativa alternativa) {
		em.persist(alternativa);
	}

	public Alternativa find(Alternativa alternativa) {
		return em.find(Alternativa.class, alternativa.getId());
	}

	public Alternativa find(long id) {
		return em.find(Alternativa.class, id);
	}

	public List<Alternativa> list() {
		return em.createQuery("from Alternativa", Alternativa.class).getResultList();
	}
}
