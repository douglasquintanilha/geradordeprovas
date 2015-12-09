package br.com.caelum.geradordeprovas.test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LiberaProvasPage {

	private WebDriver driver;

	public LiberaProvasPage(WebDriver driver) {
		this.driver = driver;
	}

	public void visita() {
		driver.get("localhost:8000/GeradorDeProvas/admin/prova/libera");
	}

	public void liberaProvaAlunos(int qntdProvas, int qntdAlunos) {
		
		List<WebElement> provas = driver.findElements(By.name("provas"));
		for(WebElement prova : provas){
			prova.click();
		}
		
		List<WebElement> alunos = driver.findElements(By.name("usuarios"));
		for(WebElement aluno : alunos){
			aluno.click();
		}
		
		alunos.get(0).submit();
		
	}

	public String liberou() {
		if (driver.getCurrentUrl().equals(
				"http://localhost:8000/GeradorDeProvas/admin/prova/salvaLiberacao")) {
			return "sim";
		}
		return "";
	}

}