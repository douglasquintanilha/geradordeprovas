package br.com.caelum.geradordeprovas.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GitHubPage {

	private WebDriver driver;

	public GitHubPage(WebDriver driver){
		this.driver = driver;
	}
	
	public void preenche(String login, String senha){

		WebElement txtLogin = driver.findElement(By.id("login_field"));
		WebElement txtSenha = driver.findElement(By.id("password"));

		txtLogin.sendKeys(login);
		txtSenha.sendKeys(senha);

		txtLogin.submit();

	}
	
}
