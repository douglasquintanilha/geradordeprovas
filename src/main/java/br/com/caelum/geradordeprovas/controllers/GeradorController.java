package br.com.caelum.geradordeprovas.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.geradordeprovas.dao.QuestaoDao;
import br.com.caelum.geradordeprovas.dao.TagDao;
import br.com.caelum.geradordeprovas.models.Questao;
import br.com.caelum.geradordeprovas.models.Tag;

@Controller
public class GeradorController {

	private QuestaoDao questaoDao;
	private TagDao tagDao;

	@Autowired
	public GeradorController(QuestaoDao questaoDao, TagDao tagDao) {
		this.questaoDao = questaoDao;
		this.tagDao = tagDao;
	}

	@RequestMapping("admin/seleciona-tag")
	public ModelAndView selecionaTag() {

		List<Tag> tags = new ArrayList<>(tagDao.list());

		ModelAndView mv = new ModelAndView("admin/seleciona-tag");

		mv.addObject("tags", tags);

		return mv;
	}

	@RequestMapping("/erro")
	public String erroJsp(){
		return "erro";
	}
	
	@RequestMapping("admin/mostra-por-tag")
	public ModelAndView mostraQuestoesPorTag(
			@RequestParam("tagSelecionada") String nomeTag) {

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