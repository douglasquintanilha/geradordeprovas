package br.com.caelum.geradordeprovas.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	private WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void visita(String root) {
		driver.get(root+ "loginForm");
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

	public String realizouLogin(String root) {
		if (driver.getCurrentUrl().equals(
				root+"liberadas"))
			return "usuario";
		if (driver.getCurrentUrl().equals(
				root+"admin/index"))
			return "admin";
		return "";
	}
}
