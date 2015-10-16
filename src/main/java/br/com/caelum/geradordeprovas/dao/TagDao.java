package br.com.caelum.geradordeprovas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.caelum.geradordeprovas.models.Tag;

@Repository
public class TagDao {

	@PersistenceContext
	private EntityManager manager;

	public List<Tag> list() {
		return manager.createQuery("from Tag", Tag.class).getResultList();
	}
	
	public void save(Tag tag){
		manager.persist(tag);
	}

	public Tag getTagPorNome(String nome) {
		return  manager
				.createQuery("select n from Tag n where n.nome = :nome",Tag.class)
				.setParameter("nome", nome)
				.getSingleResult();
	}
	
	public boolean existeTag(Tag tag) {
		List<Tag> tags = manager.createQuery("select n from Tag n where n.nome = :nome",Tag.class).setParameter("nome", tag.getNome()).getResultList();
		
		return tags.size() > 0;
	}
	
}
