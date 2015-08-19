package com.caelum.geradordeprovas.DAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.caelum.geradordeprovas.models.Alternativa;

@Repository
public class AlternativaDao {
	
	@PersistenceContext
	private EntityManager manager;
	
	public void save(Alternativa alternativa) {
		manager.persist(alternativa);
	}


}
