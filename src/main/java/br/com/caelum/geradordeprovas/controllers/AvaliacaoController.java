package br.com.caelum.geradordeprovas.controllers;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.geradordeprovas.dao.AvaliacaoDao;
import br.com.caelum.geradordeprovas.dao.ProvaDao;
import br.com.caelum.geradordeprovas.models.Avaliacao;
import br.com.caelum.geradordeprovas.models.Prova;
import br.com.caelum.geradordeprovas.models.Usuario;
import br.com.caelum.geradordeprovas.services.LiberacaoService;

@Controller
@RequestMapping("/avaliacao")
@Scope("request")
public class AvaliacaoController {

	private Usuario usuarioLogado;
	private AvaliacaoDao avaliacaoDao;
	private LiberacaoService liberacaoService;
	private ProvaDao provaDao;

	@Autowired
	public AvaliacaoController(@Qualifier("usuarioLogado") Usuario usuarioLogado, LiberacaoService liberacaoService,
			AvaliacaoDao avaliacaoDao, ProvaDao provaDao) {
		this.liberacaoService = liberacaoService;
		this.provaDao = provaDao;
		this.usuarioLogado = usuarioLogado;
		this.avaliacaoDao = avaliacaoDao;
	}

	@RequestMapping("/")
	public ModelAndView escolheProva(@RequestParam("avalicaoId") Prova avaliacao, HttpSession session) {

		List<Avaliacao> avaliacoes = avaliacaoDao.getAvaliacoesPor(usuarioLogado, avaliacao);
		if (avaliacoes.isEmpty()) {
			session.setAttribute("horarioInicio", Calendar.getInstance());
			return new ModelAndView("realiza-prova").addObject("prova", provaDao.getProva(avaliacao.getId()).embaralha());

		}

		ModelAndView mv = new ModelAndView("lista-provas-realizadas").addObject("avaliacoes", avaliacoes);
		mv.addObject("idProva", avaliacao.getId());

		return mv;
	}

	@Transactional
	@RequestMapping(value = "correcao", method = { RequestMethod.POST })
	public ModelAndView corrigePost(@ModelAttribute("avaliacao") Avaliacao avaliacao, HttpSession session,
			@ModelAttribute("prova") Prova prova) {
		if (session.getAttribute("avaliacao") != null) {
			return new ModelAndView("redirect:refaz");
		} else {

			System.out.println("NOME " + prova.getNome());
			System.out.println(avaliacao.getId());

			// avaliacao.setHorarioInicio((Calendar)
			// session.getAttribute("horarioInicio"));
			// avaliacao.setHorarioFim(Calendar.getInstance());
			// avaliacao.setUsuario(usuarioLogado);
			// avaliacao.validaDuracao();
			// avaliacao.corrige();
			// avaliacaoDao.save(avaliacao);
			session.setAttribute("avaliacao", avaliacao);
			return new ModelAndView("redirect:correcao");
		}
	}

	@Transactional
	@RequestMapping(value = "correcao", method = { RequestMethod.GET })
	public ModelAndView corrigeGet(@ModelAttribute("avaliacao") Avaliacao avaliacao, HttpSession session) {
		avaliacao = (Avaliacao) session.getAttribute("avaliacao");
		avaliacao = avaliacaoDao.atualiza(avaliacao);
		return new ModelAndView("corrigido").addObject("avaliacao", avaliacao);
	}

	@RequestMapping("refaz")
	public String refazerAvaliacao() {
		return "refazAvaliacao";
	}
}