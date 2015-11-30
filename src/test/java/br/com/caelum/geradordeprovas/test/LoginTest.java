package br.com.caelum.geradordeprovas.test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginTest {

	private FirefoxDriver driver;

	
	@Before
	public void inicializa() {
		this.driver = new FirefoxDriver();
	}
	
	@Test
	public void loginDeUsuario() {

		LoginPage pagina = new LoginPage(driver);
		pagina.visita();
		String login = "usuario";
		String senha = "12345";
		pagina.preenche(login, senha);

		assertEquals(pagina.realizouLogin(), "usuario");

	}

	@Test
	public void loginDeAdmin() {

		LoginPage pagina = new LoginPage(driver);
		pagina.visita();
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
