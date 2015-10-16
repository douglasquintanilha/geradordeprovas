package br.com.caelum.geradordeprovas.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.Assert.*;

public class AdicionaQuestaoTest {

	private FirefoxDriver driver;

	@Before
	public void inicializa() {
		this.driver = new FirefoxDriver();
	}

	@Test
	public void adicionaQuestaoComum() {

		AdicionaQuestaoPage pagina = new AdicionaQuestaoPage(driver);
		pagina.visita();
		List<String> preencheCampos = new ArrayList<>();

		preencheCampos.add("adicionaQuestaoComAlternativaASendoCerta");
		preencheCampos.add("Certa A");
		preencheCampos.add("Errada B");
		preencheCampos.add("Errada C");
		preencheCampos.add("Errada D");
		preencheCampos.add("Errada E");
		preencheCampos.add("botaoA");

		pagina.adiciona(preencheCampos);

		assertTrue(pagina.foiParaPaginaOk());
	}

	@Test
	public void adicionaQuestaoSemMarcarAlternativaCerta() {

		AdicionaQuestaoPage pagina = new AdicionaQuestaoPage(driver);
		pagina.visita();
		List<String> preencheCampos = new ArrayList<>();

		preencheCampos.add("adicionaQuestaoComAlternativaASendoCerta");
		preencheCampos.add("Certa A");
		preencheCampos.add("Errada B");
		preencheCampos.add("Errada C");
		preencheCampos.add("Errada D");
		preencheCampos.add("Errada E");
		preencheCampos.add("");

		pagina.adiciona(preencheCampos);

		assertTrue(!(pagina.foiParaPaginaOk()));

	}

	@After
	public void encerra() {
		driver.close();
	}

}
