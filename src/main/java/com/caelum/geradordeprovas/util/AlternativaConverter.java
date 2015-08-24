package com.caelum.geradordeprovas.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;

import com.caelum.geradordeprovas.models.Alternativa;

public class AlternativaConverter implements Converter<String, Alternativa> {

	@Override
	public Alternativa convert(String alternativa) {
		Alternativa alternativaObj = new Alternativa();
		
		alternativaObj.setDescricao(alternativa);

		return alternativaObj;
	}

}