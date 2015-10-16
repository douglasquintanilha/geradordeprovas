package br.com.caelum.geradordeprovas.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.geradordeprovas.dao.AlternativaDao;
import br.com.caelum.geradordeprovas.dao.ProvaDao;
import br.com.caelum.geradordeprovas.dao.UsuarioDao;
import br.com.caelum.geradordeprovas.models.Prova;
import br.com.caelum.geradordeprovas.models.RelatorioProva;
import br.com.caelum.geradordeprovas.models.Resposta;
import br.com.caelum.geradordeprovas.models.Usuario;

@Controller
public class UsuarioController {

	private AlternativaDao alternativaDao;
	private ProvaDao provaDao;
	private UsuarioDao usuarioDao;

	@Autowired
	public UsuarioController(AlternativaDao alternativaDao, ProvaDao provaDao,
			UsuarioDao usuarioDao) {
		this.alternativaDao = alternativaDao;
		this.provaDao = provaDao;
		this.usuarioDao = usuarioDao;
	}

	@RequestMapping("correcao-prova")
	public ModelAndView corrigeProvas(
			@ModelAttribute("resposta") Resposta marcadas,
			@RequestParam("provaId") Long id) {

		Prova prova = provaDao.getProva(id);

		if(!marcadas.validacao(prova)){
			ModelAndView mv = new ModelAndView("realiza-prova");
			mv.addObject("validacao", false);
			return mv;
		}

		RelatorioProva rp = prova.corrige(marcadas, alternativaDao);
		ModelAndView mv = new ModelAndView("corrigido");
		mv.addObject("relatorio", rp);

		return mv;
	}

	@RequestMapping("provas-liberadas")
	public ModelAndView provasLiberadas(HttpSession sessao) {

		Usuario usuario = (Usuario) sessao.getAttribute("usuario");
		Usuario user = usuarioDao.getUsuario(usuario.getLogin());
		
		List<Prova> provas = new ArrayList<>(user.getProvas());

		ModelAndView mv = new ModelAndView("provas-liberadas");
		mv.addObject("provas", provas);

		return mv;
	}

	@RequestMapping("escolhe-prova")
	public ModelAndView escolheProva(@RequestParam("provaId") Long id) {

		Prova prova = provaDao.getProva(id);

		ModelAndView mv = new ModelAndView("realiza-prova");
		mv.addObject("prova", prova);

		return mv;
	}

}
