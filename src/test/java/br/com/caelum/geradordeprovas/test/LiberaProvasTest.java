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
public class LiberaProvasTest {

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