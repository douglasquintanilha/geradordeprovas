package br.com.caelum.geradordeprovas.test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CriaUsuarioTest {

	private FirefoxDriver driver;

	@Before
	public void inicializa() {
		this.driver = new FirefoxDriver();
		LoginPage pagina = new LoginPage(driver);
		pagina.visita();
		String login = "admin";
		String senha = "12345";
		pagina.preenche(login, senha);
	}

	@Test
	public void adicionaUsuarioComum() {

		CriaUsuarioPage pagina = new CriaUsuarioPage(driver);
		pagina.visita();
		String nome = "romulo";
		String senha = "12345";

		pagina.preenche(nome, senha, false);
		assertEquals(pagina.realizouLogin(), "entrou");
	}

	public void adicionaAdmin() {

		CriaUsuarioPage pagina = new CriaUsuarioPage(driver);
		pagina.visita();
		String nome = "admin";
		String senha = "12345";

		pagina.preenche(nome, senha, true);
		assertEquals(pagina.realizouLogin(), "entrou");
	}

	@After
	public void encerra() {
		driver.close();
	}

}
