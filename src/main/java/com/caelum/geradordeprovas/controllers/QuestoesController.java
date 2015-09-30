package com.caelum.geradordeprovas.controllers;
	
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.caelum.geradordeprovas.models.Questao;
import com.caelum.geradordeprovas.services.QuestaoFacade;

@Controller
public class QuestoesController {

	private QuestaoFacade questaoBo;
	
	@Autowired
	public QuestoesController(QuestaoFacade questaoBo ) {
		this.questaoBo = questaoBo;
	}

	@RequestMapping("adiciona-questao")
	public String mostraAdicionaQuestaoForm(Questao questao) {
		return "adiciona-questao";
	}
	
	@RequestMapping("questao-adicionada")
	public String ok(){
		return "questao-adicionada";
	}

	@org.springframework.transaction.annotation.Transactional
	@RequestMapping("salva-questao")
	public String salva(@ModelAttribute("questao") @Valid Questao questao, BindingResult result,Model model) {
		if(result.hasErrors()){
			model.addAttribute("alternativa", questao.getAlternativa());
			return "adiciona-questao";
		}
		
		System.out.println(questao.getAlternativa());
		questaoBo.salva(questao);
		
		return "redirect:questao-adicionada";
	}

}
