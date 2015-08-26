package com.caelum.geradordeprovas.controllers;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	@Transactional()
	public String salva(@ModelAttribute("questao") @Valid Questao questao, BindingResult result,Model model) {
		if(result.hasErrors()){
			
			model.addAttribute("alternativa", questao.getAlternativa());
			return "adiciona-questao";
		}
		return "ok";
	}

}
