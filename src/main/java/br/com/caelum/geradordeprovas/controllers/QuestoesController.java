package br.com.caelum.geradordeprovas.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.geradordeprovas.models.Questao;
import br.com.caelum.geradordeprovas.services.QuestaoFacade;

@Controller
@RequestMapping("/admin/questao")
public class QuestoesController {

	private QuestaoFacade questaoBo;

	@Autowired
	public QuestoesController(QuestaoFacade questaoBo) {
		this.questaoBo = questaoBo;
	}

	@RequestMapping("/adiciona")
	public String mostraAdicionaQuestaoForm(Questao questao) {
		return "admin/adiciona-questao";
	}

	@org.springframework.transaction.annotation.Transactional
	@RequestMapping("/salva")
	public String salva(@ModelAttribute("questao") @Valid Questao questao,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			System.out.println("Alternativa[0]"
					+ questao.getAlternativa().get(0));
			model.addAttribute("alternativa", questao.getAlternativa());
			System.out.println("Erros" + result.getAllErrors());
			return "admin/adiciona-questao";
		}
		questaoBo.salva(questao);

		return "admin/questao-adicionada";
	}
}
