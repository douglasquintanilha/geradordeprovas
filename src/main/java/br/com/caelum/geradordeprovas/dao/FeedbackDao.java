package br.com.caelum.geradordeprovas.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.caelum.geradordeprovas.models.Feedback;

@Repository
public class FeedbackDao {

	@PersistenceContext
	private EntityManager manager;

	public void save(Feedback feedback) {
		manager.persist(feedback);
	}

	
}
