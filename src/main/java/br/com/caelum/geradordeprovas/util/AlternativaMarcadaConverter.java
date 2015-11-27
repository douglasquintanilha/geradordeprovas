package br.com.caelum.geradordeprovas.util;

import org.springframework.core.convert.converter.Converter;

import br.com.caelum.geradordeprovas.dao.AlternativaDao;
import br.com.caelum.geradordeprovas.models.Alternativa;
import br.com.caelum.geradordeprovas.models.AlternativaMarcada;

public class AlternativaMarcadaConverter implements Converter<String, AlternativaMarcada> {

	private AlternativaDao alternativaDao;

	public AlternativaMarcadaConverter(AlternativaDao alternativaDao) {
		this.alternativaDao = alternativaDao;
	}
	
	
	@Override
	public AlternativaMarcada convert(String alternativaId) {
		AlternativaMarcada AlternativaMarcadaObj = new AlternativaMarcada();
		
		Alternativa alternativa = alternativaDao.getAlternativaPorId(Long.parseLong(alternativaId));
		
		AlternativaMarcadaObj.setDescricao(alternativa.getDescricao());
		AlternativaMarcadaObj.setId(alternativa.getId());
		AlternativaMarcadaObj.setAlternativaCorreta(alternativa.isAlternativaCorreta());
		
		return AlternativaMarcadaObj;
	}

}