package br.com.caelum.geradordeprovas.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.geradordeprovas.dao.AlternativaDao;
import br.com.caelum.geradordeprovas.dao.ProvaDao;
import br.com.caelum.geradordeprovas.models.Alternativa;
import br.com.caelum.geradordeprovas.models.Prova;
import br.com.caelum.geradordeprovas.models.RelatorioProva;
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

	@RequestMapping("correcao-prova")
	public ModelAndView corrigeProvas(
			@ModelAttribute @RequestParam("alternativas[]") List<Alternativa> alternativas,
			@RequestParam("provaId") Prova prova) {

		// if(!marcadas.validacao(prova)){
		// ModelAndView mv = new ModelAndView("realiza-prova");
		// mv.addObject("validacao", false);
		// return mv;
		// }

		RelatorioProva rp = prova.corrige(alternativas);

		return new ModelAndView("corrigido").addObject("relatorio", rp);

	}

	@RequestMapping("provas-liberadas")
	public ModelAndView provasLiberadas() {
		
		List<Prova> provas = new ArrayList<>(usuarioLogado.getProvas());
		return new ModelAndView("provas-liberadas").addObject("provas", provas);
	}

	@RequestMapping("escolhe-prova")
	public ModelAndView escolheProva(@RequestParam("provaId") Prova prova) {
		return new ModelAndView("realiza-prova").addObject("prova", prova);

	}

}
