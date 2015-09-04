package com.caelum.geradordeprovas.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.caelum.geradordeprovas.DAO.AlternativaDao;
import com.caelum.geradordeprovas.DAO.QuestaoDao;
import com.caelum.geradordeprovas.DAO.TagDao;
import com.caelum.geradordeprovas.models.Alternativa;
import com.caelum.geradordeprovas.models.Questao;
import com.caelum.geradordeprovas.models.Resposta;
import com.caelum.geradordeprovas.models.Tag;

@Controller
public class GeradorController {

	private QuestaoDao questaoDao;
	private AlternativaDao alternativaDao;
	private TagDao tagDao;

	@Autowired
	public GeradorController(QuestaoDao questaoDao,
			AlternativaDao alternativaDao, TagDao tagDao) {
		this.questaoDao = questaoDao;
		this.alternativaDao = alternativaDao;
		this.tagDao = tagDao;
	}

	@RequestMapping("prova")
	public ModelAndView montaProvaPorLista(ArrayList<Questao> questoes,
			ArrayList<Alternativa> alternativas) {

		if (questoes.isEmpty() || alternativas.isEmpty()) {
			ModelAndView erro = new ModelAndView("erro");
			return erro;
		}

		ModelAndView mv = new ModelAndView("prova-total");
		mv.addObject("listaQuestoes", questoes);
		mv.addObject("listaAlternativas", alternativas);
		return mv;
	}

	@RequestMapping("correcao-prova")
	public ModelAndView corrigeProvas(
			@ModelAttribute("resposta") Resposta marcadas) {

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

	@RequestMapping("mostra-por-tag")
	public ModelAndView selecionaTag() {

		List<Tag> tags = new ArrayList<>(tagDao.list());

		System.out.println(tags);

		ModelAndView mv = new ModelAndView();

		mv.addObject("tags", tags);

		return mv;
	}

	@RequestMapping("mostra-por-tag2")
	public ModelAndView mostraQuestoesPorTag(
			@RequestParam("tagSelecionada") String tag) {

		List<Tag> tags = new ArrayList<>(tagDao.list());

		System.out.println(tags);

		List<Integer> idQuestoes = new ArrayList<>(tagDao.getQuestoesPorTag(tag));

		List<Questao> questoes = new ArrayList<>();
		
		for(Integer id : idQuestoes){
			questoes.add(questaoDao.getQuestaoPorId(id));
		}
		
		ModelAndView mv = new ModelAndView();

		mv.addObject("tags", tags);
		mv.addObject("questoes", questoes);

		return mv;
	}

}