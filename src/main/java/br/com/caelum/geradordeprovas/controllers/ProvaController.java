package br.com.caelum.geradordeprovas.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.geradordeprovas.dao.ProvaDao;
import br.com.caelum.geradordeprovas.dao.QuestaoDao;
import br.com.caelum.geradordeprovas.dao.UsuarioDao;
import br.com.caelum.geradordeprovas.models.Prova;
import br.com.caelum.geradordeprovas.models.Questao;
import br.com.caelum.geradordeprovas.models.Usuario;

@Controller
@RequestMapping("/admin/prova")
public class ProvaController {

	private QuestaoDao questaoDao;
	private ProvaDao provaDao;
	private UsuarioDao usuarioDao;

	@Autowired
	public ProvaController(QuestaoDao questaoDao, ProvaDao provaDao,
			UsuarioDao usuarioDao) {
		this.questaoDao = questaoDao;
		this.provaDao = provaDao;
		this.usuarioDao = usuarioDao;
	}

	@RequestMapping("/monta")
	public ModelAndView montarProvaView() {
		ModelAndView mv = new ModelAndView("admin/montar-prova");
		List<Questao> questoes = questaoDao.list();
		mv.addObject("questoes", questoes);
		return mv;
	}

	@Transactional
	@RequestMapping("/salvaProva")
	public ModelAndView salvaProva(@Valid @ModelAttribute("prova") Prova prova,
			BindingResult result) {
		if (result.hasErrors()) {
			ModelAndView mv = new ModelAndView("admin/montar-prova",
					result.getModel());
			List<Questao> questoes = questaoDao.list();
			mv.addObject("questoes", questoes);
			return mv;
		}

		provaDao.save(prova);

		ModelAndView mv = new ModelAndView("admin/prova-adicionada");

		return mv;
	}

	@RequestMapping("/libera")
	public ModelAndView liberaProva() {

		List<Usuario> usuarios = new ArrayList<>(usuarioDao.list());
		List<Prova> provas = new ArrayList<>(provaDao.list());

		ModelAndView mv = new ModelAndView("admin/libera-prova");
		mv.addObject("usuarios", usuarios);
		mv.addObject("provas", provas);

		return mv;

	}

	@Transactional
	@RequestMapping("/salvaLiberacao")
	public ModelAndView salvaLiberacao(
			@RequestParam("provas") List<Long> provasId,
			@RequestParam("usuarios") List<Long> usuarios) {

		List<Prova> provas = new ArrayList<>(provaDao.getProvasPorIds(provasId));

		if (provasId.isEmpty() || usuarios.isEmpty()) {
			ModelAndView mv = new ModelAndView("redirect:libera");
			mv.addObject("validacao", false);
			return mv;
		}

		for (Long usuarioId : usuarios) {
			usuarioDao.salvaProvasLiberadas(usuarioId, provas);
		}
		ModelAndView mv = new ModelAndView("admin/provas-liberadas");
		return mv;
	}
	

}
