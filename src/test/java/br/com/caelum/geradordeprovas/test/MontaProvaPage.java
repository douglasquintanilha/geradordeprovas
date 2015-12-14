package br.com.caelum.geradordeprovas.test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MontaProvaPage {

	private WebDriver driver;

	public MontaProvaPage(WebDriver driver) {
		this.driver = driver;
	}

	public void visita(String root) {
		driver.get(root + "admin/prova/monta");
	}

	public void preenche(String nomeProva) {

		WebElement txtNomeProva = driver.findElement(By.id("nomeProva"));
		txtNomeProva.sendKeys(nomeProva);

		List<WebElement> questoes = driver.findElements(By.name("questoes"));
		for (WebElement questao : questoes) {
			questao.click();
		}

		txtNomeProva.submit();
	}

	public String montouProva() {
		if (driver.getCurrentUrl().equals(
				"http://localhost:8000/GeradorDeProvas/admin/prova/salvaProva")) {
			return "sim";
		}
		return "nao";
	}

}
