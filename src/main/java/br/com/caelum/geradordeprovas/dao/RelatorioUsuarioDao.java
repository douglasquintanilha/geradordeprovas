package br.com.caelum.geradordeprovas.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.caelum.geradordeprovas.models.RelatorioUsuario;

@Repository
public class RelatorioUsuarioDao {

	@PersistenceContext
	private EntityManager manager;	
	
	public void save(RelatorioUsuario relatorioUsuario){
		manager.persist(relatorioUsuario);
	}
	
}
