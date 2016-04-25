package br.com.caelum.geradordeprovas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.caelum.geradordeprovas.models.Turma;

@Repository
public class TurmaDao {

	@PersistenceContext
	private EntityManager em;
	
	public List<Turma> list(){
		return em.createQuery("from Turma t", Turma.class).getResultList();
	}

	public void save(Turma turma) {
		em.persist(turma);
	}

	public Turma getTurma(Long id) {
		return em.find(Turma.class, id);
	}

	
}
