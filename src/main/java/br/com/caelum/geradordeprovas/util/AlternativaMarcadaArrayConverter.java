package br.com.caelum.geradordeprovas.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;

import br.com.caelum.geradordeprovas.dao.AlternativaDao;
import br.com.caelum.geradordeprovas.models.Alternativa;
import br.com.caelum.geradordeprovas.models.AlternativaMarcada;

public class AlternativaMarcadaArrayConverter implements Converter<String[], List<AlternativaMarcada>> {

	private AlternativaDao alternativaDao;

	public AlternativaMarcadaArrayConverter(AlternativaDao alternativaDao) {
		this.alternativaDao = alternativaDao;
	}

	@Override
	public List<AlternativaMarcada> convert(String[] AlternativaMarcadasIds) {
		List<AlternativaMarcada> AlternativaMarcadasList = new ArrayList<>();
		for (String id : AlternativaMarcadasIds) {
			Alternativa alternativa = alternativaDao.find(Long.parseLong(id));
			AlternativaMarcada alternativaMarcada = new AlternativaMarcada(alternativa.getId(),
					alternativa.getDescricao(), alternativa.isAlternativaCorreta());
			AlternativaMarcadasList.add(alternativaMarcada);
		}

		return AlternativaMarcadasList;
	}
}
