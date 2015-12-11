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
@ContextConfiguration(classes = {JpaConfigurator.class,TestConfigurator.class})
@ActiveProfiles("test")
public class LoginTest {
	
	private FirefoxDriver driver;
	
	@Autowired
	@Qualifier("rootAplicacao")
	private String root;
	
	
	@Before
	public void inicializa() {
		this.driver = new FirefoxDriver();
	}
	
	@Test
	public void loginDeUsuario() {
		LoginPage pagina = new LoginPage(driver);
		pagina.visita(root);
		String login = "usuario";
		String senha = "12345";
		pagina.preenche(login, senha);

		assertEquals(pagina.realizouLogin(), "usuario");

	}

	public void loginDeAdmin() {

		LoginPage pagina = new LoginPage(driver);
		pagina.visita(root);
		String login = "admin";
		String senha = "12345";
		pagina.preenche(login, senha);

		assertEquals(pagina.realizouLogin(), "admin");

	}

	@After
	public void encerra() {
		driver.close();
	}

}
