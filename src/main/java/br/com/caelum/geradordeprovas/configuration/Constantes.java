package br.com.caelum.geradordeprovas.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.stereotype.Component;

@Component
public class Constantes {

	private Properties properties = new Properties();
	private String environment = System.getenv("ENVIRONMENT");
	
	public Constantes() {
		try {
			InputStream configs = Constantes.class.getResourceAsStream("/"+environment+".properties");
			properties.load(configs);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public String getProperty(String s) {
		return properties.getProperty(s);
	}

}
