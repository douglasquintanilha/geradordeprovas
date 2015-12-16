package br.com.caelum.geradordeprovas.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.geradordeprovas.models.AlternativaMarcada;
import br.com.caelum.geradordeprovas.models.Avaliacao;

public class AvaliacaoTest {
	
	Avaliacao avaliacao;
	
	@Before
	public void criaUmaAvalicao() {
		List<AlternativaMarcada> marcadas = new ArrayList<AlternativaMarcada>();
		
		AlternativaMarcada a1 = new AlternativaMarcada();
		AlternativaMarcada a2 = new AlternativaMarcada();
		AlternativaMarcada a3 = new AlternativaMarcada();
		
		a1.setAlternativaCorreta(false);
		a2.setAlternativaCorreta(true);
		a3.setAlternativaCorreta(true);
		
		marcadas.add(a1);
		marcadas.add(a2);
		marcadas.add(a3);		
		this.avaliacao.setAlternativasMarcadas(marcadas);
	}

	@Test
	public void verifica_se_avalicao_foi_corrigida_corretamente() {					
		this.avaliacao.corrige();
		
		assertEquals(this.avaliacao.getNota(),2);		
	}
	
	

}
