package com.caelum.geradordeprovas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.caelum.geradordeprovas.DAO.AlternativaDao;
import com.caelum.geradordeprovas.DAO.QuestaoDao;
import com.caelum.geradordeprovas.models.Alternativa;
import com.caelum.geradordeprovas.models.Questao;

@Controller
public class GeradorController {

	@Autowired
	private QuestaoDao questaoDao;

	@Autowired
	private AlternativaDao alternativaDao;
	
	@RequestMapping("prova-total")
	public ModelAndView mostraQuestoes() {

		List<Questao> questoes = questaoDao.list();
		List<Alternativa> alternativas = alternativaDao.list();

		ModelAndView mv = new ModelAndView("prova-total");
		mv.addObject("listaQuestoes", questoes);
		mv.addObject("listaAlternativas", alternativas);
		return mv;
	}
	
}
