package br.com.caelum.geradordeprovas.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.caelum.geradordeprovas.models.Prova;
import br.com.caelum.geradordeprovas.models.Questao;
import br.com.caelum.geradordeprovas.models.Usuario;

@Repository
public class ProvaDao {

	@PersistenceContext
	private EntityManager em;

	public void save(Prova prova) {
		em.persist(prova);
	}

	public void merge(Prova prova) {
		em.merge(prova);
	}

	public List<Prova> list() {
		return em.createQuery("from Prova p", Prova.class).getResultList();
	}

	public Prova find(Long id) {
		return em.find(Prova.class, id);
	}

	public List<Prova> findBy(List<Long> ids) {

		List<Prova> provas = new ArrayList<>();
		for (Long id : ids) {
			provas.add(em.find(Prova.class, id));
		}
		return provas;

	}

	public List<Prova> getProvasLiberadasByUsuario(Usuario usuario) {
		return em.createQuery("select p from Usuario u JOIN u.provas p where u.id = :usuarioId", Prova.class)
				.setParameter("usuarioId", usuario.getId()).getResultList();
	}

	public List<Prova> getProvasPor(Questao questao) {
		return em.createQuery("select p from Prova p JOIN p.questoes q where q.id = :questaoId", Prova.class)
				.setParameter("questaoId", questao.getId()).getResultList();
	}

}