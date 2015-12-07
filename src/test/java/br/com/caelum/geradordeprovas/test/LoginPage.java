package br.com.caelum.geradordeprovas.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	private WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void visita() {
		driver.get("localhost:8000/GeradorDeProvas/loginForm");
	}

	public void preenche(String login, String senha) {

		WebElement txtLogin = driver.findElement(By.name("login"));
		WebElement txtSenha = driver.findElement(By.name("senha"));

		txtLogin.sendKeys(login);
		txtSenha.sendKeys(senha);

		txtLogin.submit();

	}
	
	public String getCurrentUrl(){
		return driver.getCurrentUrl();
	}

	public String realizouLogin() {
		if (driver.getCurrentUrl().equals(
				"http://localhost:8000/GeradorDeProvas/liberadas"))
			return "usuario";
		if (driver.getCurrentUrl().equals(
				"http://localhost:8000/GeradorDeProvas/admin/index"))
			return "admin";
		return "";
	}
}
