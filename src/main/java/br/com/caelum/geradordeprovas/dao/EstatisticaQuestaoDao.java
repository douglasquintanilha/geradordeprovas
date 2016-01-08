package br.com.caelum.geradordeprovas.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.caelum.geradordeprovas.models.EstatisticaQuestao;

@Repository
public class EstatisticaQuestaoDao {

	@PersistenceContext
	private EntityManager manager;

	public void save(EstatisticaQuestao eQuestao) {
		manager.persist(eQuestao);
	}
	
	public EstatisticaQuestao getEstatisticaQuestaoPorId(Long id) {
		return manager.find(EstatisticaQuestao.class, id);
	}
}
