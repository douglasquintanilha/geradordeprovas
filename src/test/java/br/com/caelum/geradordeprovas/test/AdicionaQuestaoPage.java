package br.com.caelum.geradordeprovas.test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdicionaQuestaoPage {

	private WebDriver driver;

	public AdicionaQuestaoPage(WebDriver driver) {
		this.driver = driver;
	}

	public void visita(String root) {
		driver.get(root + "admin/questao/adiciona");
	}

	public void preenche(String titulo, String tags, String alternativaCorreta,
			String alternativaErrada) {

		//Preenche titulo e tags
		WebElement txtTitulo = driver.findElement(By.id("titulo"));
		txtTitulo.sendKeys(titulo);
		WebElement txtTags = driver.findElement(By.id("tags"));
		txtTags.sendKeys(tags);
		
		//preenche alternativas como todas erradas
		List<WebElement> alternativas = driver.findElements(By.id("alternativa"));
		for(WebElement alternativa : alternativas){
			alternativa.sendKeys(alternativaErrada);
		}
		
		//preenche a A como correta
		WebElement botaoA = driver.findElement(By.id("botaoA"));
		botaoA.click();
		alternativas.get(0).clear();
		alternativas.get(0).sendKeys(alternativaCorreta);

		//envia o form na qual o Titulo está
		txtTitulo.submit();
	}

	public String adicionouQuestao(String root) {
		if (driver.getCurrentUrl().equals(
				root+"admin/questao/salva")) {
			return "sim";
		}
		return "";
	}

}
