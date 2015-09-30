package com.caelum.geradordeprovas.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.caelum.geradordeprovas.DAO.AlternativaDao;
import com.caelum.geradordeprovas.DAO.ProvaDao;
import com.caelum.geradordeprovas.DAO.QuestaoDao;
import com.caelum.geradordeprovas.DAO.TagDao;
import com.caelum.geradordeprovas.models.Alternativa;
import com.caelum.geradordeprovas.models.Prova;
import com.caelum.geradordeprovas.models.Questao;
import com.caelum.geradordeprovas.models.Resposta;
import com.caelum.geradordeprovas.models.Tag;

@Controller
public class GeradorController {

	private QuestaoDao questaoDao;
	private AlternativaDao alternativaDao;
	private TagDao tagDao;
	private ProvaDao provaDao;

	@Autowired
	public GeradorController(QuestaoDao questaoDao,
			AlternativaDao alternativaDao, TagDao tagDao,ProvaDao provaDao) {
		this.questaoDao = questaoDao;
		this.alternativaDao = alternativaDao;
		this.tagDao = tagDao;
		this.provaDao = provaDao;
	}

	@RequestMapping("prova-aluno")
	public ModelAndView montaProvaPorLista() {

		List<Questao> questoes = questaoDao.list();
		
		if (questoes.isEmpty()) {
			ModelAndView erro = new ModelAndView("erro");
			return erro;
		}

		ModelAndView mv = new ModelAndView("prova-aluno");
		mv.addObject("questoes", questoes);
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

	@RequestMapping("admin/seleciona-tag")
	public ModelAndView selecionaTag() {

		List<Tag> tags = new ArrayList<>(tagDao.list());

		ModelAndView mv = new ModelAndView("admin/seleciona-tag");

		mv.addObject("tags", tags);

		return mv;
	}
	
	@RequestMapping("admin/mostra-por-tag")
	public ModelAndView mostraQuestoesPorTag(
			@RequestParam("tagSelecionada") String nomeTag) {

		List<Questao> questoes = new ArrayList<>();
		questoes = questaoDao.getQuestoesPorTag(nomeTag);

		// System.out.println(questoes.get(0).getClass());

		ModelAndView mv = new ModelAndView("admin/mostra-por-tag");

		List<Tag> tags = new ArrayList<>(tagDao.list());
		mv.addObject("tags", tags);
		
		mv.addObject("questoes", questoes);
		mv.addObject("nomeTag", nomeTag);

		return mv;

	}
	
	@RequestMapping("admin/montar-prova")
	public ModelAndView montarProvaView(){
		ModelAndView mv = new ModelAndView("admin/montar-prova");
		List<Questao> questoes = questaoDao.list();
		mv.addObject("questoes",questoes);
		return mv;
	}
	
	@Transactional
	@RequestMapping("admin/salvar-prova")
	public ModelAndView salvaProva(@Valid @ModelAttribute("prova") Prova prova, BindingResult result){
		if(result.hasErrors()){
			ModelAndView mv = new ModelAndView("admin/montar-prova",result.getModel());
			List<Questao> questoes = questaoDao.list();
			mv.addObject("questoes",questoes);
			return mv;
		}
		
		provaDao.save(prova);
		
		ModelAndView mv = new ModelAndView("admin/prova-adicionada");
		
		return mv;
	}
	

}