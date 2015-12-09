package br.com.caelum.geradordeprovas.test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LiberaProvasTest {

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
	public void liberaUmaProvaParaTodosOsAlunos(){
		
		LiberaProvasPage pagina = new LiberaProvasPage(driver);
		pagina.visita();
		
		int qntdProvas = 1, qntdAlunos = 2;
		pagina.liberaProvaAlunos(qntdProvas, qntdAlunos);
		
		assertEquals(pagina.liberou(), "sim");
	}
	
	@After
	public void encerra() {
		driver.close();
	}

}