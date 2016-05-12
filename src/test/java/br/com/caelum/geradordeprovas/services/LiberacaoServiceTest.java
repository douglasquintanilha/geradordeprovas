package br.com.caelum.geradordeprovas.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import br.com.caelum.geradordeprovas.dao.AvaliacaoDao;
import br.com.caelum.geradordeprovas.databuilder.QuestaoBuilder;
import br.com.caelum.geradordeprovas.models.Avaliacao;
import br.com.caelum.geradordeprovas.models.Prova;
import br.com.caelum.geradordeprovas.models.Questao;
import br.com.caelum.geradordeprovas.models.Turma;
import br.com.caelum.geradordeprovas.models.Usuario;

public class LiberacaoServiceTest {

	@Test
	public void retornaTrueSeProvaFoiAtulizadaDepoisDaUltimaAvaliacao() {

		Set<Questao> questoesImutaveis = new HashSet<Questao>();
		Prova prova = new Prova();

		Calendar diaDepois = Calendar.getInstance();
		diaDepois.set(2016, 04, 04, 12, 00, 00);
		prova.setUpdatedAt(diaDepois);

		Avaliacao avaliacao = new Avaliacao("Prova Teste", questoesImutaveis, 1l);
		Calendar diaAntes = Calendar.getInstance();
		diaAntes.set(2016, 04, 03, 12, 00, 00);

		avaliacao.setCreatedAt(diaAntes);

		LiberacaoService ls = new LiberacaoService(new AvaliacaoDao());

		boolean resultado = ls.provaFoiAtualizadaDepoisDaUltimaAvaliacao(avaliacao, prova);
		assertTrue(resultado);
	}

	@Test
	public void retornaFalsoSeProvaNaoFoiAtulizadaDepoisDaUltimaAvaliacao() {

		Set<Questao> questoesImutaveis = new HashSet<Questao>();
		Prova prova = new Prova();

		Calendar diaDepois = Calendar.getInstance();
		diaDepois.set(2016, 04, 04, 12, 00, 00);
		prova.setUpdatedAt(diaDepois);

		Avaliacao avaliacao = new Avaliacao("Prova Teste", questoesImutaveis, 1l);
		Calendar diaAntes = Calendar.getInstance();
		diaAntes.set(2016, 04, 05, 12, 00, 00);

		avaliacao.setCreatedAt(diaAntes);

		LiberacaoService ls = new LiberacaoService(new AvaliacaoDao());

		boolean resultado = ls.provaFoiAtualizadaDepoisDaUltimaAvaliacao(avaliacao, prova);
		assertFalse(resultado);
	}

	@Test
	public void geraAvaliacaoDeProvasQueNuncaForamAtualizadas() {

		QuestaoBuilder questaoBuilder = new QuestaoBuilder();

		List<Questao> questoes = questaoBuilder.geraListaDeQuestoes();

		Prova prova1 = new Prova();
		prova1.setNome("Prova Teste");
		prova1.setId(1l);
		prova1.setQuestoes(questoes);
		Calendar horario = Calendar.getInstance();
		prova1.setUpdatedAt(horario);

		List<Prova> provas = Arrays.asList(prova1);

		Avaliacao avaliacaoEsperada = new Avaliacao();
		avaliacaoEsperada.setNomeProva("Prova Teste");
		avaliacaoEsperada.setQuestoesImutaveis(new HashSet<Questao>(questoes));
		avaliacaoEsperada.setProvaId(1l);
		avaliacaoEsperada.setCreatedAt(horario);

		AvaliacaoDao avaliacaoDaoFalso = mock(AvaliacaoDao.class);
		when(avaliacaoDaoFalso.getUltimaAvaliacaoCriada(prova1)).thenReturn(avaliacaoEsperada);

		LiberacaoService ls = new LiberacaoService(avaliacaoDaoFalso);
		List<Avaliacao> avaliacoesGeradas = ls.geraAvaliacoes(provas);

		assertEquals(1, avaliacoesGeradas.size());
		assertEquals(avaliacaoEsperada.getQuestoesImutaveis(), avaliacoesGeradas.get(0).getQuestoesImutaveis());
		assertEquals(avaliacaoEsperada.getNomeProva(), avaliacoesGeradas.get(0).getNomeProva());
		assertEquals(avaliacaoEsperada.getProvaId(), avaliacoesGeradas.get(0).getProvaId());

	}

	@Test
	public void geraAvaliacaoDeProvasQueForamAtualizadas() {

		QuestaoBuilder questaoBuilder = new QuestaoBuilder();
		List<Questao> questoes = questaoBuilder.geraListaDeQuestoes();
		Prova prova1 = new Prova();
		prova1.setNome("Prova Teste");
		prova1.setId(1l);
		prova1.setQuestoes(questoes);
		Calendar horario = Calendar.getInstance();
		prova1.setUpdatedAt(horario);
		List<Prova> provas = Arrays.asList(prova1);

		Avaliacao avaliacaoEsperada = new Avaliacao();
		avaliacaoEsperada.setNomeProva("Prova Teste");
		avaliacaoEsperada.setQuestoesImutaveis(new HashSet<Questao>(questoes));
		avaliacaoEsperada.setProvaId(1l);
		avaliacaoEsperada.setCreatedAt(Calendar.getInstance());

		Questao questao1 = questaoBuilder.geraQuestaoDefault();
		questao1.setTitulo("Titulo Fake");
		Questao questao2 = questaoBuilder.geraQuestaoDefault();
		questao2.setTitulo("Titulo Fake");
		List<Questao> questoesFake = Arrays.asList(questao1, questao2);

		Avaliacao avaliacaoFake = new Avaliacao();
		avaliacaoFake.setNomeProva("Prova Antiga");
		avaliacaoFake.setQuestoesImutaveis(new HashSet<Questao>(questoesFake));
		avaliacaoFake.setProvaId(1l);
		Calendar antigao = Calendar.getInstance();
		antigao.set(1999, 1, 20);
		avaliacaoFake.setCreatedAt(antigao);

		AvaliacaoDao avaliacaoDaoFalso = mock(AvaliacaoDao.class);
		when(avaliacaoDaoFalso.getUltimaAvaliacaoCriada(prova1)).thenReturn(avaliacaoFake);

		LiberacaoService ls = new LiberacaoService(avaliacaoDaoFalso);
		List<Avaliacao> avaliacoesGeradas = ls.geraAvaliacoes(provas);

		System.out.println(avaliacoesGeradas.get(0).getNomeProva());

		assertEquals(1, avaliacoesGeradas.size());
		assertEquals(avaliacaoEsperada.getQuestoesImutaveis(), avaliacoesGeradas.get(0).getQuestoesImutaveis());
		assertEquals(avaliacaoEsperada.getNomeProva(), avaliacoesGeradas.get(0).getNomeProva());
		assertEquals(avaliacaoEsperada.getProvaId(), avaliacoesGeradas.get(0).getProvaId());

	}

	@Test(expected=NullPointerException.class)
	public void naoLiberaAvaliacoesSeListaDeUsuariosEhNula() {
		List<Usuario> usuarios = null;

		AvaliacaoDao avaliacaoDaoFalso = mock(AvaliacaoDao.class);

		LiberacaoService ls = new LiberacaoService(avaliacaoDaoFalso);

		Avaliacao avaliacao2 = new Avaliacao();
		avaliacao2.setId(2l);
		Avaliacao avaliacao1 = new Avaliacao();
		avaliacao1.setId(1l);

		List<Avaliacao> avaliacoes = Arrays.asList(avaliacao1, avaliacao2);
		ls.liberaAvaliacoesParaUsuarios(avaliacoes, usuarios);
	}

	@Test
	public void liberaAvaliacaoParaUmUsuario() {
		Usuario usuario = new Usuario();

		List<Usuario> usuarios = Arrays.asList(usuario);

		AvaliacaoDao avaliacaoDaoFalso = mock(AvaliacaoDao.class);

		LiberacaoService ls = new LiberacaoService(avaliacaoDaoFalso);

		Avaliacao avaliacao2 = new Avaliacao();
		avaliacao2.setId(2l);
		Avaliacao avaliacao1 = new Avaliacao();
		avaliacao1.setId(1l);

		List<Avaliacao> avaliacoes = Arrays.asList(avaliacao1, avaliacao2);
		ls.liberaAvaliacoesParaUsuarios(avaliacoes, usuarios);

		assertEquals(2, usuarios.get(0).getAvaliacoes().size());
		assertTrue(usuarios.get(0).getAvaliacoes().contains(avaliacao1));
		assertTrue(usuarios.get(0).getAvaliacoes().contains(avaliacao2));
	}

	@Test
	public void liberaAvaliacaoParaVariosUsuarios() {
		Usuario usuario = new Usuario();
		Usuario usuario2 = new Usuario();

		List<Usuario> usuarios = Arrays.asList(usuario, usuario2);

		AvaliacaoDao avaliacaoDaoFalso = mock(AvaliacaoDao.class);
		LiberacaoService ls = new LiberacaoService(avaliacaoDaoFalso);

		Avaliacao avaliacao2 = new Avaliacao();
		avaliacao2.setId(2l);
		Avaliacao avaliacao1 = new Avaliacao();
		avaliacao1.setId(1l);
		List<Avaliacao> avaliacoes = Arrays.asList(avaliacao1, avaliacao2);

		ls.liberaAvaliacoesParaUsuarios(avaliacoes, usuarios);

		assertEquals(2, usuarios.get(0).getAvaliacoes().size());
		assertTrue(usuarios.get(0).getAvaliacoes().contains(avaliacao1));
		assertTrue(usuarios.get(0).getAvaliacoes().contains(avaliacao2));

		assertEquals(2, usuarios.get(1).getAvaliacoes().size());
		assertTrue(usuarios.get(1).getAvaliacoes().contains(avaliacao1));
		assertTrue(usuarios.get(1).getAvaliacoes().contains(avaliacao2));
	}

	@Test
	public void liberaAvaliacaoParaUmaTurma() {
		Turma turma = new Turma();

		List<Turma> turmas = Arrays.asList(turma);

		AvaliacaoDao avaliacaoDaoFalso = mock(AvaliacaoDao.class);

		LiberacaoService ls = new LiberacaoService(avaliacaoDaoFalso);

		Avaliacao avaliacao2 = new Avaliacao();
		avaliacao2.setId(2l);
		Avaliacao avaliacao1 = new Avaliacao();
		avaliacao1.setId(1l);

		List<Avaliacao> avaliacoes = Arrays.asList(avaliacao1, avaliacao2);
		ls.liberaAvaliacoesParaTurmas(avaliacoes, turmas);

		assertEquals(2, turmas.get(0).getAvaliacoes().size());
		assertTrue(turmas.get(0).getAvaliacoes().contains(avaliacao1));
		assertTrue(turmas.get(0).getAvaliacoes().contains(avaliacao2));
	}

	@Test
	public void liberaAvaliacaoParaVariasTurmas() {
		Turma turma = new Turma();
		Turma turma2 = new Turma();

		List<Turma> turmas = Arrays.asList(turma, turma2);

		AvaliacaoDao avaliacaoDaoFalso = mock(AvaliacaoDao.class);

		LiberacaoService ls = new LiberacaoService(avaliacaoDaoFalso);

		Avaliacao avaliacao2 = new Avaliacao();
		avaliacao2.setId(2l);
		Avaliacao avaliacao1 = new Avaliacao();
		avaliacao1.setId(1l);

		List<Avaliacao> avaliacoes = Arrays.asList(avaliacao1, avaliacao2);
		ls.liberaAvaliacoesParaTurmas(avaliacoes, turmas);

		assertEquals(2, turmas.get(0).getAvaliacoes().size());
		assertTrue(turmas.get(0).getAvaliacoes().contains(avaliacao1));
		assertTrue(turmas.get(0).getAvaliacoes().contains(avaliacao2));

		assertEquals(2, turmas.get(1).getAvaliacoes().size());
		assertTrue(turmas.get(1).getAvaliacoes().contains(avaliacao1));
		assertTrue(turmas.get(1).getAvaliacoes().contains(avaliacao2));
	}

}