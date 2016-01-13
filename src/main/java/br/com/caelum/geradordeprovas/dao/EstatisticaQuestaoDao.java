package br.com.caelum.geradordeprovas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.caelum.geradordeprovas.models.EstatisticaQuestao;
import br.com.caelum.geradordeprovas.models.Questao;

@Repository
public class EstatisticaQuestaoDao {

	@PersistenceContext
	private EntityManager manager;

	public void save(EstatisticaQuestao eQuestao) {
		manager.persist(eQuestao);
	}

	public List<EstatisticaQuestao> list() {
		return manager.createQuery("from EstatisticaQuestao e",
				EstatisticaQuestao.class).getResultList();
	}

	public EstatisticaQuestao getEstatisticaQuestaoPorId(Long id) {
		return manager.find(EstatisticaQuestao.class, id);
	}
}
