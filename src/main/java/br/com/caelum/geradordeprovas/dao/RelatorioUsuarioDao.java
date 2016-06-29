package br.com.caelum.geradordeprovas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.caelum.geradordeprovas.models.Prova;
import br.com.caelum.geradordeprovas.models.RelatorioUsuario;

@Repository
public class RelatorioUsuarioDao {

	@PersistenceContext
	private EntityManager manager;

	public void save(RelatorioUsuario relatorioUsuario) {
		manager.persist(relatorioUsuario);
	}

	public List<RelatorioUsuario> findBy(Prova prova) {

		List<RelatorioUsuario> relatorios = manager
				.createQuery("select r from Avaliacao a JOIN a.relatoriosUsuarios r where a.provaId =:provaId",
						RelatorioUsuario.class).setParameter("provaId", prova.getId()).getResultList();

		return relatorios;
	}
	


}
