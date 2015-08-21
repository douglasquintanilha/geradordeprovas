package com.caelum.geradordeprovas.controllers;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.caelum.geradordeprovas.DAO.AlternativaDao;
import com.caelum.geradordeprovas.DAO.QuestaoDao;
import com.caelum.geradordeprovas.models.Alternativa;
import com.caelum.geradordeprovas.models.Questao;

@Controller
public class QuestoesController {

	@Autowired
	private QuestaoDao questaoDao;

	@Autowired
	private AlternativaDao alternativaDao;

	@RequestMapping("adiciona-questao")
	public String mostraAdicionaQuestaoForm() {
		return "adiciona-questao";
	}

	@RequestMapping("salva-questao")
	@Transactional(propagation = Propagation.REQUIRED)
	public String salva(
			@Valid Questao questao,
			BindingResult result,
			@RequestParam("alternativa") ArrayList<Alternativa> alternativa,
			@RequestParam("alternativaCorreta") String alternativaCorreta) {
		
		 if(result.hasErrors()) {
			 System.out.println("Entrou");
		      return "adiciona-questao";
		    } 

		questaoDao.save(questao);

		int alternativaCorretaInt = Integer.parseInt(alternativaCorreta);
		for (int i = 0; i < 5; i++) {
			if (alternativaCorretaInt == i) {
				alternativa.get(i).setAlternativaCorreta(true);
				alternativa.get(i).setQuestao(questao);
				alternativaDao.save(alternativa.get(i));

			} else {
				alternativa.get(i).setQuestao(questao);
				alternativaDao.save(alternativa.get(i));
			}
		}

		return "ok";
	}

}
