package br.com.caelum.geradordeprovas.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;

import br.com.caelum.geradordeprovas.models.AlternativaMarcada;

public class AlternativaMarcadaArrayConverter implements Converter<String[],List<AlternativaMarcada>>{

	@Override
	public List<AlternativaMarcada> convert(String[] AlternativaMarcadas) {
		List<AlternativaMarcada> AlternativaMarcadasList = new ArrayList<>();
		for (String AlternativaMarcada : AlternativaMarcadas) {
			AlternativaMarcada AlternativaMarcadaObj = new AlternativaMarcada();
			AlternativaMarcadaObj.setDescricao(AlternativaMarcada);
			AlternativaMarcadasList.add(AlternativaMarcadaObj);
		}
		
		return AlternativaMarcadasList;
	}
}
