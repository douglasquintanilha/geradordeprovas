package com.caelum.geradordeprovas.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.caelum.geradordeprovas.DAO.AlternativaDao;
import com.caelum.geradordeprovas.DAO.QuestaoDao;
import com.caelum.geradordeprovas.models.Alternativa;
import com.caelum.geradordeprovas.models.Questao;
import com.caelum.geradordeprovas.models.Resposta;

@Controller
public class GeradorController {

	private QuestaoDao questaoDao;
	private AlternativaDao alternativaDao;
	
	@Autowired
	public GeradorController(QuestaoDao questaoDao,AlternativaDao alternativaDao) {
		this.questaoDao = questaoDao;
		this.alternativaDao = alternativaDao;
	}
	
	@RequestMapping("prova-total")
	public ModelAndView mostraQuestoes() {

		List<Questao> questoes = questaoDao.list();
		List<Alternativa> alternativas = alternativaDao.list();

		ModelAndView mv = new ModelAndView("prova-total");
		mv.addObject("listaQuestoes", questoes);
		mv.addObject("listaAlternativas", alternativas);
		return mv;
	}

	@RequestMapping("correcao-prova")
	public ModelAndView corrigeProvas(@ModelAttribute("resposta") Resposta marcadas) {

		
		List<Long> respostas = marcadas.getAlternativas();
		List<Alternativa> acertou = new ArrayList<>();
		List<Alternativa> errou = new ArrayList<>();

		for (int i = 0; i < respostas.size(); i++) {
			if (alternativaDao.getAlternativaPorId(respostas.get(i))
					.isAlternativaCorreta() == true) {
				acertou.add(alternativaDao.getAlternativaPorId(respostas.get(i)));
			} else {
				errou.add(alternativaDao.getAlternativaPorId(respostas.get(i)));
			}
		}

		ModelAndView mv = new ModelAndView("corrigido");
		mv.addObject("nota", acertou.size());
		mv.addObject("total", marcadas.getAlternativas().size());
		mv.addObject("certas", acertou);
		mv.addObject("erradas", errou);

		return mv;
	}

}
