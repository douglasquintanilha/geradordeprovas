package br.com.caelum.geradordeprovas.databuilder;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.geradordeprovas.models.Questao;
import br.com.caelum.geradordeprovas.models.Tag;

public class QuestaoBuilderTest {

	@Test
	public void testaSeGeraListaDeQuestoesFunciona() {

		QuestaoBuilder builder = new QuestaoBuilder();
		List<Questao> questoes = builder.geraListaDeQuestoes();

		Assert.assertEquals("Questao Teste", questoes.get(0).getTitulo());
		Assert.assertEquals(true, questoes.get(0).getTags().contains(new Tag(1l, "Java")));
		
		
	}

}
