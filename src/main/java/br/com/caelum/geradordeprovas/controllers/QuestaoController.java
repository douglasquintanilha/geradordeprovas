package br.com.caelum.geradordeprovas.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.geradordeprovas.dao.ProvaDao;
import br.com.caelum.geradordeprovas.dao.QuestaoDao;
import br.com.caelum.geradordeprovas.models.Prova;
import br.com.caelum.geradordeprovas.models.Questao;
import br.com.caelum.geradordeprovas.services.QuestaoService;

@Controller
@RequestMapping("/admin/questao")
public class QuestaoController {

	private QuestaoService questaoBo;
	private QuestaoDao questaoDao;
	private ProvaDao provaDao;

	@Autowired
	public QuestaoController(QuestaoService questaoBo, QuestaoDao questaoDao, ProvaDao provaDao) {
		this.questaoBo = questaoBo;
		this.questaoDao = questaoDao;
		this.provaDao = provaDao;
	}

	@RequestMapping("/adiciona")
	public String mostraAdicionaQuestaoForm(Questao questao) {
		return "admin/adiciona-questao";
	}

	@RequestMapping("/edita")
	public ModelAndView editaQuestaoView() {
		List<Questao> questoes = questaoDao.list();
		List<Prova> provas = provaDao.list();
		ModelAndView mv = new ModelAndView("admin/edita-questao-view");
		mv.addObject("questoes", questoes);
		mv.addObject("provas", provas);
		return mv;
	}

	@RequestMapping("/edita/{questaoId}")
	public ModelAndView editaProva(@PathVariable(value = "questaoId") Long id) {
		Questao questao = questaoDao.getQuestaoPorId(id);
		return new ModelAndView("/admin/edita-questao").addObject("questao", questao);
	}

	@Transactional
	@RequestMapping("/edita/salva")
	public String salvaQuestaoEditada(@ModelAttribute("questao") @Valid Questao questaoEditada, BindingResult result,
			Model model, @RequestParam("questaoId") Long id) {
		if (result.hasErrors()) {
			model.addAttribute("alternativa", questaoEditada.getAlternativa());
			return "admin/adiciona-questao";
		}

		Questao questao = questaoDao.getQuestaoPorId(id);
		questao.edita(questaoEditada);
		questaoBo.edita(questao);

		return "admin/questao-editada";
	}

	@Transactional
	@RequestMapping("/salva")
	public String salva(@ModelAttribute("questao") @Valid Questao questao, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("alternativa", questao.getAlternativa());
			return "admin/adiciona-questao";
		}
		questaoBo.salva(questao);

		return "admin/questao-adicionada";
	}

}
