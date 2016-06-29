package br.com.caelum.geradordeprovas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.geradordeprovas.dao.AvaliacaoDao;
import br.com.caelum.geradordeprovas.dao.ProvaDao;
import br.com.caelum.geradordeprovas.dao.RelatorioUsuarioDao;
import br.com.caelum.geradordeprovas.dao.UsuarioDao;
import br.com.caelum.geradordeprovas.models.Avaliacao;
import br.com.caelum.geradordeprovas.models.RelatorioUsuario;
import br.com.caelum.geradordeprovas.models.Usuario;

@Controller
@RequestMapping("/avaliacao")
@Scope("request")
public class AvaliacaoController {

	private Usuario usuarioLogado;
	private AvaliacaoDao avaliacaoDao;
	private RelatorioUsuarioDao relatorioUsuarioDao;
	private ProvaDao provaDao;
	private UsuarioDao usuarioDao;

	@Autowired
	public AvaliacaoController(UsuarioDao usuarioDao, ProvaDao provaDao, RelatorioUsuarioDao relatorioUsuarioDao,
			@Qualifier("usuarioLogado") Usuario usuarioLogado, AvaliacaoDao avaliacaoDao) {
		this.relatorioUsuarioDao = relatorioUsuarioDao;
		this.usuarioLogado = usuarioLogado;
		this.avaliacaoDao = avaliacaoDao;
		this.provaDao = provaDao;
		this.usuarioDao = usuarioDao;
	}

	@Transactional
	@RequestMapping("")
	public ModelAndView realizaAvaliacao(@RequestParam("id") Long id) {
		Avaliacao avaliacao = avaliacaoDao.getAvaliacaoMaisRecente(provaDao.find(id));
		usuarioDao.merge(usuarioLogado).liberaAvaliacaoMaisRecente(avaliacao);
		return new ModelAndView("realiza-avaliacao").addObject("avaliacao", avaliacao);
	}

	@Transactional
	@RequestMapping("/corrige")
	public ModelAndView corrigeAvaliacao(Avaliacao avaliacao) {
		RelatorioUsuario relatorio = avaliacao.corrige(usuarioLogado);
		relatorioUsuarioDao.save(relatorio);
		avaliacaoDao.getAvaliacao(avaliacao.getId()).addRelatorio(relatorio);

		ModelAndView mv = new ModelAndView("avaliacao-corrigida");
		mv.addObject("relatorio", relatorio);
		mv.addObject("avaliacao", avaliacaoDao.getAvaliacao(avaliacao.getId()));

		return mv;
	}

}