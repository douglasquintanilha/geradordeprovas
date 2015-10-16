package br.com.caelum.geradordeprovas.util;

import org.springframework.core.convert.converter.Converter;

import br.com.caelum.geradordeprovas.dao.ProvaDao;
import br.com.caelum.geradordeprovas.models.Prova;

public class ProvaConverter implements Converter<String,Prova> {

	private ProvaDao provaDao;
	
	public ProvaConverter(ProvaDao provaDao){
		this.provaDao = provaDao;
	}
	

	@Override
	public Prova convert(String provaId) {
		return provaDao.getProva(Long.parseLong(provaId));
	}
}
