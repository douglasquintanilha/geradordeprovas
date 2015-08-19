package com.caelum.geradordeprovas.controllers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.caelum.geradordeprovas.DAO.QuestaoDao;
import com.caelum.geradordeprovas.models.Alternativa;
import com.caelum.geradordeprovas.models.Questao;

@Controller
public class QuestoesController {
	
	@Autowired
	private QuestaoDao questaoDao;
	
	
	@PersistenceContext
	EntityManager em;
	
	
	@RequestMapping("adiciona-questao")
	public String adiciona(){
		return "adiciona-questao";
	}
	
	
	@RequestMapping("questoes")
	public String salva(Questao questao, Alternativa alternativa){
		return "questoes/ok/";
	}
	
}
