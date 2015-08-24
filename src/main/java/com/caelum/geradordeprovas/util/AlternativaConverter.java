package com.caelum.geradordeprovas.util;

import java.util.ArrayList;

import org.springframework.core.convert.converter.Converter;

import com.caelum.geradordeprovas.models.Alternativa;

public class AlternativaConverter implements
		Converter<String[], ArrayList<Alternativa>> {

	@Override
	public ArrayList<Alternativa> convert(String[] alternativas) {
		ArrayList<Alternativa> alternativasList = new ArrayList<>();
		System.out.println("Silvio Gatao");
		for (String alternativa : alternativas) {
			Alternativa novaAlternativa = new Alternativa();
			novaAlternativa.setDescricao(alternativa);
			alternativasList.add(novaAlternativa);
		}
		return alternativasList;
	}

}