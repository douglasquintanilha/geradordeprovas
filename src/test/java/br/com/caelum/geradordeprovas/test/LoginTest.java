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
		String senha = "usuario";
		pagina.preenche(login, senha);

		assertEquals(pagina.realizouLogin(), "usuario");

	}

	@Test
	public void loginDeAdmin() {

		LoginPage pagina = new LoginPage(driver);
		pagina.visita();
		String login = "admin";
		String senha = "1234";
		pagina.preenche(login, senha);

		assertEquals(pagina.realizouLogin(), "admin");

	}

	@Test
	public void loginComUsuarioInexistenteNoGitHub() {

		LoginPage pagina = new LoginPage(driver);
		pagina.visita();
		String login = "admin";
		String senha = "12345";
		System.out.println(pagina.getCurrentUrl());
		pagina.clicaGitHub().preenche(login, senha);
		System.out.println("OIE");
		assertEquals(pagina.realizouLogin(), "failGitHub");

	}

	@After
	public void encerra() {
		driver.close();
	}

}
