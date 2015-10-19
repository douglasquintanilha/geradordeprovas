package br.com.caelum.geradordeprovas.util;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import br.com.caelum.geradordeprovas.dao.AlternativaDao;
import br.com.caelum.geradordeprovas.models.Alternativa;

public class AlternativaFormatter implements Formatter<Alternativa> {

	private AlternativaDao alternativaDao;

	public AlternativaFormatter(AlternativaDao alternativaDao) {
		this.alternativaDao = alternativaDao;
	}
	
	@Override
	public String print(Alternativa alternativa, Locale locale) {
		return alternativa.getId().toString();
	}

	@Override
	public Alternativa parse(String id, Locale locale) throws ParseException {
		Long idLong = Long.valueOf(id);
		return alternativaDao.getAlternativaPorId(idLong);
	}

	
}
