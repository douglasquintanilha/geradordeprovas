package br.com.caelum.geradordeprovas.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.caelum.geradordeprovas.models.Prova;
import br.com.caelum.geradordeprovas.models.Usuario;

@Repository
public class ProvaDao {

	@PersistenceContext
	private EntityManager manager;

	public void save(Prova prova) {
		manager.persist(prova);
	}
	
	public void update(Prova prova){
		manager.merge(prova);
	}

	public List<Prova> list() {
		return manager.createQuery("from Prova p", Prova.class).getResultList();
	}

	public Prova getProva(Long id) {
		return manager.find(Prova.class, id);
	}
	
	public List<Prova> getProvasPorIds(List<Long> ids) {

		List<Prova> provas = new ArrayList<>();
		for (Long id : ids) {
			provas.add(manager.find(Prova.class, id));
		}
		return provas;

	}

	public List<Prova> getProvasLiberadasByUsuario(Usuario usuario) {
		return manager
				.createQuery(
						"select p from Usuario u JOIN u.provas p where u.id = :usuarioId",
						Prova.class).setParameter("usuarioId", usuario.getId())
				.getResultList();
	}
	
	
}
