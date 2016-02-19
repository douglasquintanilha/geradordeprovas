package br.com.caelum.geradordeprovas.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.geradordeprovas.models.Questao;
import br.com.caelum.geradordeprovas.services.QuestaoService;

@Controller
@RequestMapping("/admin/questao")
public class QuestaoController {

	private QuestaoService questaoBo;

	@Autowired
	public QuestaoController(QuestaoService questaoBo) {
		this.questaoBo = questaoBo;
	}

	@RequestMapping("/adiciona")
	public String mostraAdicionaQuestaoForm(Questao questao) {
		return "admin/adiciona-questao";
	}

	@Transactional
	@RequestMapping("/salva")
	public String salva(@ModelAttribute("questao") @Valid Questao questao,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("alternativa", questao.getAlternativa());
			return "admin/adiciona-questao";
		}
		questaoBo.salva(questao);

		return "admin/questao-adicionada";
	}

}
