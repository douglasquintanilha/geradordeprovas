package br.com.caelum.geradordeprovas.test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AdicionaQuestaoTest {

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
	public void adicionaUmaQuestaoSemMarkdown(){
		
		AdicionaQuestaoPage pagina = new AdicionaQuestaoPage(driver);
		pagina.visita();
		
		String titulo = "Questao 1 adicionada com Selenium";
		String tags = "teste, selenium";
		String alternativaCorreta = "correta";
		String alternativaErrada = "errada";
		
		
		pagina.preenche(titulo, tags, alternativaCorreta, alternativaErrada);
		assertEquals(pagina.adicionouQuestao(), "sim");
	}
	
	@After
	public void encerra() {
		driver.close();
	}

}
