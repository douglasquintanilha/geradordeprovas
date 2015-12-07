package br.com.caelum.geradordeprovas.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.geradordeprovas.dao.AlternativaDao;
import br.com.caelum.geradordeprovas.dao.ProvaDao;
import br.com.caelum.geradordeprovas.models.Prova;
import br.com.caelum.geradordeprovas.models.Usuario;

@Controller
@Scope("request")
public class UsuarioController {

	private AlternativaDao alternativaDao;
	private ProvaDao provaDao;
	private Usuario usuarioLogado;

	@Autowired
	public UsuarioController(AlternativaDao alternativaDao, ProvaDao provaDao,Usuario usuarioLogado) {
		this.alternativaDao = alternativaDao;
		this.provaDao = provaDao;
		this.usuarioLogado = usuarioLogado;
	}


	@RequestMapping("/liberadas")
	public ModelAndView provasLiberadas() {
		
		List<Prova> provas = new ArrayList<>(usuarioLogado.getProvas());
		return new ModelAndView("provas-liberadas").addObject("provas", provas);
	}

	@RequestMapping("/avaliacao/{provaId}")
	public ModelAndView escolheProva(@PathVariable("provaId") Prova prova) {
		return new ModelAndView("realiza-prova").addObject("prova", provaDao.getProva(prova.getId()));
	}
	
}
