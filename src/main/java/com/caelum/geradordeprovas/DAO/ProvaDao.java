package com.caelum.geradordeprovas.DAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.caelum.geradordeprovas.models.Prova;

@Repository
public class ProvaDao {

	@PersistenceContext
	private EntityManager manager;
	
	public void save(Prova prova) {
		manager.persist(prova);
	}
	
	
}
