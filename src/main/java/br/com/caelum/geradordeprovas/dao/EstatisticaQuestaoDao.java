package br.com.caelum.geradordeprovas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.caelum.geradordeprovas.models.EstatisticaQuestao;

@Repository
public class EstatisticaQuestaoDao {

	@PersistenceContext
	private EntityManager em;

	public void save(EstatisticaQuestao eQuestao) {
		em.persist(eQuestao);
	}

	public List<EstatisticaQuestao> list() {
		return em.createQuery("from EstatisticaQuestao e", EstatisticaQuestao.class).getResultList();
	}

	public EstatisticaQuestao find(Long id) {
		return em.find(EstatisticaQuestao.class, id);
	}
}
