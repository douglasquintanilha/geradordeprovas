package br.com.caelum.geradordeprovas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.caelum.geradordeprovas.models.Prova;
import br.com.caelum.geradordeprovas.models.Turma;

@Repository
public class TurmaDao {

	@PersistenceContext
	private EntityManager em;

	public void salvaProvasLiberadas(Turma turma, List<Prova> provas) {
		turma.adicionaProvas(provas);
	}

	public List<Turma> list() {
		return em.createQuery("from Turma t", Turma.class).getResultList();
	}

	public Turma procuraTurmaPor(String nome) {
		try {
			Turma turma = em.createQuery("select t from Turma t where t.nome=:nome", Turma.class).setParameter("nome", nome)
					.getSingleResult();
			return turma;
		} catch (Exception e) {
			return null;
		}
	}

	public void save(Turma turma) {
		em.persist(turma);
	}

	public Turma getTurma(Long id) {
		return em.find(Turma.class, id);
	}

}
