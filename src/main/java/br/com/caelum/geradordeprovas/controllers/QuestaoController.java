package br.com.caelum.geradordeprovas.controllers;

import java.util.ArrayList;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.caelum.geradordeprovas.dao.ProvaDao;
import br.com.caelum.geradordeprovas.dao.QuestaoDao;
import br.com.caelum.geradordeprovas.dao.TagDao;
import br.com.caelum.geradordeprovas.models.Prova;
import br.com.caelum.geradordeprovas.models.Questao;
import br.com.caelum.geradordeprovas.models.Tag;
import br.com.caelum.geradordeprovas.services.QuestaoService;

@Controller
@RequestMapping("/admin/questao")
public class QuestaoController {

	private QuestaoService questaoService;
	private QuestaoDao questaoDao;
	private ProvaDao provaDao;
	private TagDao tagDao;

	@Autowired
	public QuestaoController(TagDao tagDao, QuestaoService questaoBo, QuestaoDao questaoDao, ProvaDao provaDao) {
		this.questaoService = questaoBo;
		this.questaoDao = questaoDao;
		this.provaDao = provaDao;
		this.tagDao = tagDao;
	}

	@RequestMapping("/adiciona")
	public String adicionaQuestaoView(Questao questao) {
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
	public ModelAndView editaQuestao(@PathVariable(value = "questaoId") Long id) {
		Questao questao = questaoDao.getQuestaoPorId(id);
		return new ModelAndView("/admin/edita-questao").addObject("questao", questao);
	}

	@Transactional
	@RequestMapping("/edita/salva")
	public String salvaEdicaoDeQuestao(@ModelAttribute("questao") @Valid Questao questaoEditada, BindingResult result,
			Model model, @RequestParam("questaoId") Long id, RedirectAttributes flash) {
		if (result.hasErrors()) {
			model.addAttribute("alternativa", questaoEditada.getAlternativa());
			return "admin/adiciona-questao";
		}

		Questao questao = questaoDao.getQuestaoPorId(id);
		questao.edita(questaoEditada);
		questaoService.edita(questao);

		flash.addFlashAttribute("questao", questao);

		return "redirect:../edita";
	}

	@Transactional
	@RequestMapping("/salva")
	public String salvaQuestao(@ModelAttribute("questao") @Valid Questao questao, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("alternativa", questao.getAlternativa());
			return "admin/adiciona-questao";
		}
		questaoService.salva(questao);

		return "admin/questao-adicionada";
	}

	@RequestMapping("/seleciona-tag")
	public ModelAndView selecionaTag() {

		List<Tag> tags = new ArrayList<>(tagDao.list());

		ModelAndView mv = new ModelAndView("admin/seleciona-tag");

		mv.addObject("tags", tags);

		return mv;
	}

	@RequestMapping("/mostra-por-tag")
	public ModelAndView mostraQuestoesPorTag(@RequestParam("tagSelecionada") String nomeTag) {

		List<Questao> questoes = new ArrayList<>();
		questoes = questaoDao.getQuestoesPorTag(nomeTag);

		ModelAndView mv = new ModelAndView("admin/mostra-por-tag");

		List<Tag> tags = new ArrayList<>(tagDao.list());
		mv.addObject("tags", tags);

		mv.addObject("questoes", questoes);
		mv.addObject("nomeTag", nomeTag);

		return mv;

	}

}
