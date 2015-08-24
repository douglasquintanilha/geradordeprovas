package com.caelum.geradordeprovas.controllers;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	public String mostraAdicionaQuestaoForm(Questao questao) {
		return "adiciona-questao";
	}

	@RequestMapping("salva-questao")
	@Transactional(propagation = Propagation.REQUIRED)
	public String salva(@ModelAttribute("questao") Questao questao) {
		
		
		return "ok";
	}

}
