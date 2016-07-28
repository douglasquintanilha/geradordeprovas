package br.com.caelum.geradordeprovas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.caelum.geradordeprovas.models.EstatisticaQuestao;
import br.com.caelum.geradordeprovas.models.Questao;

@Repository
public class QuestaoDao {

	@PersistenceContext
	private EntityManager manager;

	public void save(Questao questao) {
		manager.persist(questao);
	}

	public Questao merge(Questao questao){
		return manager.merge(questao);
	}
	
	public void save(List<Questao> questoes) {
		for (Questao questao : questoes) {
			manager.persist(questao);
		}
	}

	public Questao getQuestaoPorId(Long id) {
		return manager.find(Questao.class, id);
	}

	public List<Questao> list() {
		return manager.createQuery("from Questao q", Questao.class).getResultList();
	}

	public List<Questao> getQuestoesPorTag(String tag) {
		List<Questao> idQuestoes = manager
				.createQuery("select q from Questao q JOIN q.tags t where t.nome =:tag", Questao.class)
				.setParameter("tag", tag).getResultList();

		return idQuestoes;
	}

	public Questao getQuestaoPor(EstatisticaQuestao estatistica) {

		Questao questao = manager
				.createQuery("select q from Questao q JOIN q.estatistica e where e.id =:idEstatistica", Questao.class)
				.setParameter("id", estatistica.getId()).getSingleResult();

		return questao;
	}

	public List<Questao> getQuestaoExceto(List<Long> ids) {
		return manager.createQuery("select p from Questao p WHERE p.id NOT IN (:ids)", Questao.class)
				.setParameter("ids", ids).getResultList();
	}
}
