package br.com.caelum.geradordeprovas.test;

import org.openqa.selenium.WebDriver;

public class AdicionaQuestaoPage {

	private WebDriver driver;

	public AdicionaQuestaoPage(WebDriver driver) {
		this.driver = driver;
	}

	public void visita() {
		driver.get("localhost:8000/GeradorDeProvas/admin/questao/adiciona");
	}

}
