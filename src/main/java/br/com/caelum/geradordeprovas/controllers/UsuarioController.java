package br.com.caelum.geradordeprovas.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.geradordeprovas.dao.AvaliacaoDao;
import br.com.caelum.geradordeprovas.dao.ProvaDao;
import br.com.caelum.geradordeprovas.models.Avaliacao;
import br.com.caelum.geradordeprovas.models.Prova;
import br.com.caelum.geradordeprovas.models.Usuario;

@Controller
@Scope("request")
public class UsuarioController {

	private ProvaDao provaDao;
	private Usuario usuarioLogado;
	private AvaliacaoDao avaliacaoDao;

	@Autowired
	public UsuarioController(@Qualifier("usuarioLogado") Usuario usuarioLogado,
			ProvaDao provaDao, AvaliacaoDao avaliacaoDao) {
		this.provaDao = provaDao;
		this.usuarioLogado = usuarioLogado;
		this.avaliacaoDao = avaliacaoDao;
	}

	@RequestMapping("/avaliacao/{provaId}")
	public ModelAndView escolheProva(@PathVariable("provaId") Prova prova,
			HttpSession session) {

		List<Avaliacao> avaliacoes = avaliacaoDao.getAvaliacoesPor(
				usuarioLogado, prova);
		if (avaliacoes.isEmpty()) {
			session.setAttribute("horarioInicio", Calendar.getInstance());
			return new ModelAndView("realiza-prova").addObject("prova",
					provaDao.getProva(prova.getId()));

		}

		ModelAndView mv = new ModelAndView("lista-provas-realizadas")
				.addObject("avaliacoes", avaliacoes);
		mv.addObject("idProva", prova.getId());

		return mv;
	}

	@RequestMapping("avaliacao/realiza")
	public ModelAndView realizaProva(@RequestParam("provaId") Prova prova,
			HttpSession session) {
		session.setAttribute("horarioInicio", Calendar.getInstance());
		return new ModelAndView("realiza-prova").addObject("prova",
				provaDao.getProva(prova.getId()));
	}

	@RequestMapping("avaliacao/realizada/{avaliacaoId}")
	public ModelAndView provaRealizada(
			@PathVariable("avaliacaoId") Avaliacao avaliacao) {
		return new ModelAndView("avaliacao-realizada").addObject("avaliacao",
				avaliacao);
	}

	@RequestMapping("/liberadas")
	public ModelAndView provasLiberadas() {

		List<Prova> provas = new ArrayList<>(usuarioLogado.getProvas());
		return new ModelAndView("provas-liberadas").addObject("provas",
				provaDao.getProvasLiberadasByUsuario(usuarioLogado));
	}

}