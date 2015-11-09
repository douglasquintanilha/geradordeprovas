package br.com.caelum.geradordeprovas.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;

import br.com.caelum.geradordeprovas.models.Alternativa;

public class AlternativaArrayConverter implements Converter<String[],List<Alternativa>>{

	@Override
	public List<Alternativa> convert(String[] alternativas) {
		List<Alternativa> alternativasList = new ArrayList<>();
		for (String alternativa : alternativas) {
			Alternativa alternativaObj = new Alternativa();
			alternativaObj.setDescricao(alternativa);
			alternativasList.add(alternativaObj);
		}
		
		return alternativasList;
	}
}
