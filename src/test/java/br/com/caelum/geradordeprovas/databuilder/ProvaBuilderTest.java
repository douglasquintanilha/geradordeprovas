package br.com.caelum.geradordeprovas.databuilder;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.geradordeprovas.models.Prova;
import br.com.caelum.geradordeprovas.models.Questao;

public class ProvaBuilderTest {

	@Test
	public void testaSeGeraProvaAPartirDeQuestoesFunciona() {

		ProvaBuilder builder = new ProvaBuilder();
		List<Questao> questoes = new QuestaoBuilder().geraListaDeQuestoes();
		Prova prova = builder.geraProvaAPartirDeQuestoes(questoes);

		Long duracao = 10l;

		Assert.assertEquals("Prova de Teste", prova.getNome());
		Assert.assertEquals("Questao Teste", prova.getQuestoes().get(0).getTitulo());
		Assert.assertEquals(duracao, prova.getDuracao());
	}
}
