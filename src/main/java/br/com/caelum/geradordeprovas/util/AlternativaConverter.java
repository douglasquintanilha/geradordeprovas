package br.com.caelum.geradordeprovas.util;

import org.springframework.core.convert.converter.Converter;

import br.com.caelum.geradordeprovas.models.Alternativa;

public class AlternativaConverter implements Converter<String, Alternativa> {

	@Override
	public Alternativa convert(String alternativa) {
		Alternativa alternativaObj = new Alternativa();
		
		alternativaObj.setDescricao(alternativa);
		return alternativaObj;
	}

}