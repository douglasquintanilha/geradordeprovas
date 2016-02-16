package br.com.caelum.geradordeprovas.test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.caelum.geradordeprovas.configuration.JpaConfigurator;
import br.com.caelum.geradordeprovas.configuration.TestConfigurator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JpaConfigurator.class, TestConfigurator.class })
@ActiveProfiles("dev")
public class MontaProvaTest {

	private FirefoxDriver driver;

	@Autowired
	@Qualifier("rootAplicacao")
	private String root;

	@Before
	public void inicializa() {
		this.driver = new FirefoxDriver();
		LoginPage pagina = new LoginPage(driver);
		pagina.visita(root);
		String login = "admin";
		String senha = "12345";
		pagina.preenche(login, senha);
	}

	@Test
	public void monta_prova_com_todas_as_questoes_do_banco() {

		MontaProvaPage pagina = new MontaProvaPage(driver);
		pagina.visita(root);

		String nomeProva = "Prova criada com Selenium";
		pagina.preenche(nomeProva);

		assertEquals(pagina.montouProva(), "sim");
	}

	@After
	public void encerra() {
		driver.close();
	}

}
