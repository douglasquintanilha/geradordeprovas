package br.com.caelum.geradordeprovas.controllers;

import java.util.Calendar;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.geradordeprovas.dao.FeedbackDao;
import br.com.caelum.geradordeprovas.dao.ProvaDao;
import br.com.caelum.geradordeprovas.dao.UsuarioDao;
import br.com.caelum.geradordeprovas.models.Avaliacao;
import br.com.caelum.geradordeprovas.models.Feedback;
import br.com.caelum.geradordeprovas.models.Prova;
import br.com.caelum.geradordeprovas.models.Usuario;

@Controller
@Scope("request")
public class UsuarioController {

	private ProvaDao provaDao;
	private Usuario usuarioLogado;
	private FeedbackDao feedbackDao;
	private UsuarioDao usuarioDao;

	@Autowired
	public UsuarioController(@Qualifier("usuarioLogado") Usuario usuarioLogado, ProvaDao provaDao,
			FeedbackDao feedbackDao, UsuarioDao usuarioDao) {
		this.provaDao = provaDao;
		this.usuarioDao = usuarioDao;
		this.feedbackDao = feedbackDao;
		this.usuarioLogado = usuarioLogado;
	}

	@RequestMapping("avaliacao/realiza")
	public ModelAndView realizaProva(@RequestParam("provaId") Prova prova, HttpSession session) {
		session.setAttribute("horarioInicio", Calendar.getInstance());
		Prova provaEmbaralhada = provaDao.find(prova.getId()).embaralha();
		return new ModelAndView("realiza-prova").addObject("prova", provaEmbaralhada);
	}

	@RequestMapping("avaliacao/realizada/{avaliacaoUuid}")
	public ModelAndView provaRealizada(@RequestParam("avaliacaoId") Avaliacao avaliacao) {
		return new ModelAndView("corrigido").addObject("avaliacao", avaliacao);
	}

	@RequestMapping("/liberadas")
	public ModelAndView provasLiberadas() {
		return new ModelAndView("avaliacoes-liberadas").addObject("provas",
				usuarioDao.getProvasDoUsuario(usuarioLogado));
	}

	@Transactional
	@RequestMapping("feedback")
	public ModelAndView feedback(@Valid @ModelAttribute("feedback") Feedback feedback, BindingResult result) {
		if (result.hasErrors()) {

		}
		feedback.setDataCriacao(Calendar.getInstance());
		feedback.setUsuario(usuarioLogado);
		feedbackDao.save(feedback);
		return new ModelAndView("feedback");
	}

}