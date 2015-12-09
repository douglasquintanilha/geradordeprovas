package br.com.caelum.geradordeprovas.test;

import org.openqa.selenium.WebDriver;

public class AdminPage {

	private WebDriver driver;

	public AdminPage(WebDriver driver) {
		this.driver = driver;
	}

	public void visita() {
		driver.get("localhost:8000/GeradorDeProvas/admin/index");
	}

}
