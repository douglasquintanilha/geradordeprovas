package br.com.caelum.geradordeprovas.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

public class TestConfigurator {

	@Qualifier("rootAplicacao")
	@Profile("local")
	@Bean
	public String getContext(){
		return "http://localhost:8000/GeradorDeProvas/";
	}
	
	@Qualifier("rootAplicacao")
	@Profile("test")
	@Bean
	public String getContextTest(){
		return "http://localhost:8000/GeradorDeProvas/";
	}
}
