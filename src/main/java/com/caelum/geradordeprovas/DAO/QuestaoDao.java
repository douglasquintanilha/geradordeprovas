package com.caelum.geradordeprovas.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.caelum.geradordeprovas.models.Questao;

@Repository
public class QuestaoDao {

	@PersistenceContext
	private EntityManager manager;

	public void save(Questao questao) {
		manager.persist(questao);
	}

	public Questao getQuestaoPorId(Long id) {
		return manager.find(Questao.class, id);
	}

	public List<Questao> list() {
		return manager.createQuery("from Questao", Questao.class)
				.getResultList();
	}

	public List<Long> getQuestoesPorIdTag(Long id) {

		List<Long> idQuestoes = new ArrayList<>();
		idQuestoes = manager
				.createQuery("select Questao_Id from Questao_Tag where tags_id = tagId")
				.setParameter("tagId", id)
				.getResultList();

		return idQuestoes;
	}
}
