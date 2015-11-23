package br.com.caelum.geradordeprovas.util;

import org.springframework.core.convert.converter.Converter;

import br.com.caelum.geradordeprovas.models.AlternativaMarcada;

public class AlternativaMarcadaConverter implements Converter<String, AlternativaMarcada> {

	@Override
	public AlternativaMarcada convert(String AlternativaMarcada) {
		AlternativaMarcada AlternativaMarcadaObj = new AlternativaMarcada();
		
		AlternativaMarcadaObj.setDescricao(AlternativaMarcada);
		return AlternativaMarcadaObj;
	}

}