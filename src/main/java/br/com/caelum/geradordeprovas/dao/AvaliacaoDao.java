package br.com.caelum.geradordeprovas.dao;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.caelum.geradordeprovas.models.Avaliacao;
@Repository
public class AvaliacaoDao {
	
	@PersistenceContext
	private EntityManager manager;

	public void save(Avaliacao avaliacao) {
		manager.persist(avaliacao);
	}

	public Avaliacao getAvaliacaoPorId(long id){
		return manager.find(Avaliacao.class, id);
	}
	
	public List<Avaliacao> list() { 
		return manager.createQuery("from Avaliacao", Avaliacao.class).getResultList();
	}

	public Avaliacao atualiza(Avaliacao avaliacao) {
		avaliacao = manager.merge(avaliacao);
		return avaliacao;
	}
}
