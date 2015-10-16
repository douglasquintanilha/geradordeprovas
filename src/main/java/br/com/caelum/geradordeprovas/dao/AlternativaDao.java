package br.com.caelum.geradordeprovas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.caelum.geradordeprovas.models.Alternativa;

@Repository
public class AlternativaDao {

	@PersistenceContext
	private EntityManager manager;

	public void save(Alternativa alternativa) {
		manager.persist(alternativa);
	}

	public Alternativa getAlternativaPorId(long id){
		return manager.find(Alternativa.class, id);
	}
	
	public List<Alternativa> list() { 
		return manager.createQuery("from Alternativa", Alternativa.class).getResultList();
	}
}
