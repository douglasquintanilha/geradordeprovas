package com.caelum.geradordeprovas.test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdicionaQuestaoPage {

	private WebDriver driver;

	public AdicionaQuestaoPage(WebDriver driver) {
		this.driver = driver;
	}

	public void visita() {
		driver.get("http://localhost:8080/adiciona-questao");
	}

	public void adiciona(List<String> campos) {

		WebElement txtTitulo = driver.findElement(By.id("titulo"));
		WebElement txtA = driver.findElement(By.id("alternativaA"));
		WebElement txtB = driver.findElement(By.id("alternativaB"));
		WebElement txtC = driver.findElement(By.id("alternativaC"));
		WebElement txtD = driver.findElement(By.id("alternativaD"));
		WebElement txtE = driver.findElement(By.id("alternativaE"));
		WebElement botaoCerto = driver.findElement(By.name(campos.get(6)));
		
		botaoCerto.click();
		txtTitulo.sendKeys(campos.get(0));
		txtA.sendKeys(campos.get(1));
		txtB.sendKeys(campos.get(2));
		txtC.sendKeys(campos.get(3));
		txtD.sendKeys(campos.get(4));
		txtE.sendKeys(campos.get(5));

		txtTitulo.submit();

	}

	public boolean foiParaOkJsp() {
		if (driver.getCurrentUrl() == "http://localhost:8080/GeradorDeProvas/ok")
			return true;
		else {
			return false;
		}

	}

}
