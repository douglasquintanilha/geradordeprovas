package br.com.caelum.geradordeprovas.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

public class TestConfigurator {

	@Qualifier("rootAplicacao")
	@Profile("dev")
	@Bean
	public String getContext(){
		return "localhost:8000/GeradorDeProvas/";
	}
	
	
	@Profile("test")
	@Bean
	public String getContextTest(){
		return "localhost:8000/GeradorDeProvas/";
	}
}
