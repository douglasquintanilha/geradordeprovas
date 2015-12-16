package br.com.caelum.geradordeprovas.util;

import org.springframework.core.convert.converter.Converter;

import br.com.caelum.geradordeprovas.dao.AvaliacaoDao;
import br.com.caelum.geradordeprovas.models.Avaliacao;

public class AvaliacaoConverter implements Converter<String,Avaliacao> {

	private AvaliacaoDao avaliacaoDao;
	
	public AvaliacaoConverter(AvaliacaoDao avaliacaoDao){
		this.avaliacaoDao = avaliacaoDao;
	}
	
	@Override
	public Avaliacao convert(String avaliacaoId) {
		return avaliacaoDao.getAvaliacao(Long.parseLong(avaliacaoId));
	}

}
