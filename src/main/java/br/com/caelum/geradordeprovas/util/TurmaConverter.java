package br.com.caelum.geradordeprovas.util;

import org.springframework.core.convert.converter.Converter;

import br.com.caelum.geradordeprovas.dao.TurmaDao;
import br.com.caelum.geradordeprovas.models.Turma;

public class TurmaConverter implements Converter<String,Turma> {

	private TurmaDao turmaDao;
	
	public TurmaConverter(TurmaDao turmaDao){
		this.turmaDao = turmaDao;
	}
	
	@Override
	public Turma convert(String turmaId) {
		return turmaDao.getTurma(Long.parseLong(turmaId));
	}
}
