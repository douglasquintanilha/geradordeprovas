package com.caelum.geradordeprovas.util;

import org.springframework.core.convert.converter.Converter;

import com.caelum.geradordeprovas.models.Alternativa;
import com.caelum.geradordeprovas.models.Questao;

public class QuestaoConverter implements Converter<String, Questao> {
	
	@Override
	public Questao convert(String questao) {
		Questao questaoObj = new Questao();
		
		questaoObj.setId(Long.parseLong(questao));
		
		return questaoObj;
	}

}
