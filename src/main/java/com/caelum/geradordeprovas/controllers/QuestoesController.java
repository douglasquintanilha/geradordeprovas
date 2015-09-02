package com.caelum.geradordeprovas.controllers;
	
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.caelum.geradordeprovas.DAO.AlternativaDao;
import com.caelum.geradordeprovas.DAO.QuestaoDao;
import com.caelum.geradordeprovas.DAO.TagDao;
import com.caelum.geradordeprovas.models.Alternativa;
import com.caelum.geradordeprovas.models.Questao;
import com.caelum.geradordeprovas.models.Tag;

@Controller
public class QuestoesController {

	
	private QuestaoDao questaoDao;
	private AlternativaDao alternativaDao;
	private TagDao tagDao;
	
	@Autowired
	public QuestoesController(QuestaoDao questaoDao,AlternativaDao alternativaDao,TagDao tagDao) {
		this.questaoDao = questaoDao;
		this.alternativaDao = alternativaDao;
		this.tagDao = tagDao;
	}

	@RequestMapping("adiciona-questao")
	public String mostraAdicionaQuestaoForm(Questao questao) {
		return "adiciona-questao";
	}
	
	@RequestMapping("ok")
	public String ok(){
		return "ok";
	}

	@RequestMapping("salva-questao")
	@Transactional()
	public String salva(@ModelAttribute("questao") @Valid Questao questao, BindingResult result,Model model) {
		if(result.hasErrors()){
			model.addAttribute("alternativa", questao.getAlternativa());
			return "adiciona-questao";
		}
			
		Set<Tag> outraListaDeTag = new HashSet<>();
		
		for(Tag tag : questao.getTags()) { 
			try { 
				Tag tagBanco = tagDao.getTagPorNome(tag.getNome());
				if(tagBanco != null){
					outraListaDeTag.add(tagBanco);
				}
			} catch(NoResultException e) { 
				tagDao.save(tag);
				outraListaDeTag.add(tag);
			}
		}
		
		questao.setTags(outraListaDeTag);
		questaoDao.save(questao);

		int alternativaCorreta = Integer.parseInt(questao.getAlternativaCorreta());
		List<Alternativa> alternativas = questao.getAlternativa();
		
		for (int i = 0; i < alternativas.size(); i++) {
			alternativas.get(i).setQuestao(questao);
			if( i == alternativaCorreta){
				alternativas.get(i).setAlternativaCorreta(true);
			}
			alternativaDao.save(alternativas.get(i));
		}
		return "redirect:ok";
	}

}
