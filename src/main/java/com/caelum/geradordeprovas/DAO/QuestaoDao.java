package com.caelum.geradordeprovas.DAO;

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
}
