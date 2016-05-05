package br.com.caelum.geradordeprovas.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.caelum.geradordeprovas.models.TesteGmail;

@Repository
public class TesteGmailDao {

	
	@PersistenceContext
	private EntityManager em;
	
	public TesteGmail getTeste(){
		return em.find(TesteGmail.class, 0);
	}
	
}
