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
import br.com.caelum.geradordeprovas.dao.QuestaoDao;
import br.com.caelum.geradordeprovas.dao.TagDao;
import br.com.caelum.geradordeprovas.dao.UsuarioDao;
import br.com.caelum.geradordeprovas.models.Alternativa;
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

		if (prova.getQuestoes().size() > marcadas.getAlternativas().size()) {
			ModelAndView mv = new ModelAndView("realiza-prova");
			mv.addObject("validacao", false);
			return mv;
		}

		List<Alternativa> alternativas = new ArrayList<>();
		for (Long idAlternativa : marcadas.getAlternativas()) {
			alternativas.add(alternativaDao.getAlternativaPorId(idAlternativa));
		}

		RelatorioProva rp = prova.corrige(alternativas);
		ModelAndView mv = new ModelAndView("corrigido");
		mv.addObject("relatorio", rp);

		return mv;
	}

	@RequestMapping("provas-liberadas")
	public ModelAndView provasLiberadas(HttpSession sessao) {

		// Não usar a sessão e passar pro Spring
		// Renomear as variaveis
		Usuario usuario = new Usuario();

		if (sessao.getAttribute("usuarioLogado") == null) {
			usuario = (Usuario) sessao.getAttribute("adminLogado");
		} else {
			usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		}

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
