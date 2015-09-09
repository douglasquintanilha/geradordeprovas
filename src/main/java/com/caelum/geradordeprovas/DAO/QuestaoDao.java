package com.caelum.geradordeprovas.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.caelum.geradordeprovas.models.Questao;
import com.caelum.geradordeprovas.models.Tag;

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

	public List<Questao> getQuestoesPorTag(Tag tag) {

		List<Questao> idQuestoes = new ArrayList<>();
		idQuestoes = manager
				.createQuery("select e from Questao e, Tag a where a.id = :tagId")
				.setParameter("tagId", tag.getId())
				.getResultList();

		
		System.out.println(idQuestoes.get(0).getClass());
		
		return idQuestoes;
	}
}
