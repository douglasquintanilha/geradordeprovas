package br.com.caelum.geradordeprovas.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CriaUsuarioPage {

	private WebDriver driver;

	public CriaUsuarioPage(WebDriver driver) {
		this.driver = driver;
	}

	public void visita() {
		driver.get("localhost:8000/GeradorDeProvas/admin/usuario/novo/form");
	}

	public void preenche(String nome, String senha, boolean admin) {

		WebElement txtNome = driver.findElement(By.id("nome"));
		WebElement txtSenha = driver.findElement(By.id("senha"));
		if(admin){
			WebElement botaoAdmin = driver.findElement(By.id("admin"));
			botaoAdmin.click();
		}
		txtNome.sendKeys(nome);
		txtSenha.sendKeys(senha);
		
		txtNome.submit();
		
		
	}

	public String realizouLogin() {
		if (driver.getCurrentUrl().equals(
				"http://localhost:8000/GeradorDeProvas/admin/usuario/novo/salva")) {
			return "entrou";
		}
		return "";
	}

}
