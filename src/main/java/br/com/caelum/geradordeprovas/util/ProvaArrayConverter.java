package br.com.caelum.geradordeprovas.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;

import br.com.caelum.geradordeprovas.dao.ProvaDao;
import br.com.caelum.geradordeprovas.models.Prova;

public class ProvaArrayConverter implements Converter<String[], List<Prova>> {

	private ProvaDao provaDao;

	public ProvaArrayConverter(ProvaDao provaDao) {
		this.provaDao = provaDao;
	}

	@Override
	public List<Prova> convert(String[] provasIds) {
		List<Prova> provas = new ArrayList<>();
		for (String provaId : provasIds) {
			provas.add(provaDao.getProva(Long.parseLong(provaId)));
		}
		return provas;
	}

}
