package br.com.caelum.geradordeprovas.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.geradordeprovas.dao.AvaliacaoDao;
import br.com.caelum.geradordeprovas.dao.ProvaDao;
import br.com.caelum.geradordeprovas.dao.RelatorioUsuarioDao;
import br.com.caelum.geradordeprovas.models.Avaliacao;
import br.com.caelum.geradordeprovas.models.Prova;
import br.com.caelum.geradordeprovas.models.RelatorioUsuario;
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
	private RelatorioUsuarioDao relatorioUsuarioDao;

	@Autowired
	public AvaliacaoController(RelatorioUsuarioDao relatorioUsuarioDao,
			@Qualifier("usuarioLogado") Usuario usuarioLogado, LiberacaoService liberacaoService,
			AvaliacaoDao avaliacaoDao, ProvaDao provaDao) {
		this.liberacaoService = liberacaoService;
		this.relatorioUsuarioDao = relatorioUsuarioDao;
		this.provaDao = provaDao;
		this.usuarioLogado = usuarioLogado;
		this.avaliacaoDao = avaliacaoDao;
	}

	@RequestMapping("")
	public ModelAndView escolheAvaliacao(@ModelAttribute("avaliacao") Avaliacao avaliacao) {
		return new ModelAndView("realiza-avaliacao").addObject("avaliacao", avaliacao);
	}

	@Transactional
	@RequestMapping("/corrige")
	public ModelAndView corrigeAvaliacao(Avaliacao avaliacao) {
		RelatorioUsuario relatorio = avaliacao.corrige(usuarioLogado);
		relatorioUsuarioDao.save(relatorio);

		ModelAndView mv = new ModelAndView("avaliacao-corrigida");
		mv.addObject("relatorio", relatorio);
		mv.addObject("avaliacao", avaliacaoDao.getAvaliacao(avaliacao.getId()));

		return mv;
	}

	@Transactional
	@RequestMapping(value = "correcao", method = { RequestMethod.POST })
	public ModelAndView corrigePost(@ModelAttribute("avaliacao") Avaliacao avaliacao, HttpSession session,
			@ModelAttribute("prova") Prova prova) {
		if (session.getAttribute("avaliacao") != null) {
			return new ModelAndView("redirect:refaz");
		} else {
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