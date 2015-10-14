package com.caelum.geradordeprovas.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.caelum.geradordeprovas.DAO.AlternativaDao;
import com.caelum.geradordeprovas.DAO.ProvaDao;
import com.caelum.geradordeprovas.DAO.QuestaoDao;
import com.caelum.geradordeprovas.DAO.TagDao;
import com.caelum.geradordeprovas.DAO.UsuarioDao;
import com.caelum.geradordeprovas.models.Alternativa;
import com.caelum.geradordeprovas.models.Prova;
import com.caelum.geradordeprovas.models.Questao;
import com.caelum.geradordeprovas.models.RelatorioProva;
import com.caelum.geradordeprovas.models.Resposta;
import com.caelum.geradordeprovas.models.Tag;
import com.caelum.geradordeprovas.models.Usuario;

@Controller
public class GeradorController {

	private QuestaoDao questaoDao;
	private AlternativaDao alternativaDao;
	private TagDao tagDao;
	private ProvaDao provaDao;
	private UsuarioDao usuarioDao;

	@Autowired
	public GeradorController(QuestaoDao questaoDao,
			AlternativaDao alternativaDao, TagDao tagDao, ProvaDao provaDao,
			UsuarioDao usuarioDao) {
		this.questaoDao = questaoDao;
		this.alternativaDao = alternativaDao;
		this.tagDao = tagDao;
		this.provaDao = provaDao;
		this.usuarioDao = usuarioDao;
	}

	@RequestMapping("admin/seleciona-tag")
	public ModelAndView selecionaTag() {

		List<Tag> tags = new ArrayList<>(tagDao.list());

		ModelAndView mv = new ModelAndView("admin/seleciona-tag");

		mv.addObject("tags", tags);

		return mv;
	}

	@RequestMapping("admin/mostra-por-tag")
	public ModelAndView mostraQuestoesPorTag(
			@RequestParam("tagSelecionada") String nomeTag) {

		List<Questao> questoes = new ArrayList<>();
		questoes = questaoDao.getQuestoesPorTag(nomeTag);

		// System.out.println(questoes.get(0).getClass());

		ModelAndView mv = new ModelAndView("admin/mostra-por-tag");

		List<Tag> tags = new ArrayList<>(tagDao.list());
		mv.addObject("tags", tags);

		mv.addObject("questoes", questoes);
		mv.addObject("nomeTag", nomeTag);

		return mv;

	}

	@RequestMapping("admin/montar-prova")
	public ModelAndView montarProvaView() {
		ModelAndView mv = new ModelAndView("admin/montar-prova");
		List<Questao> questoes = questaoDao.list();
		mv.addObject("questoes", questoes);
		return mv;
	}

	@Transactional
	@RequestMapping("admin/salvar-prova")
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

	@RequestMapping("admin/libera-prova")
	public ModelAndView liberaProva() {

		List<Usuario> usuarios = new ArrayList<>(usuarioDao.list());
		List<Prova> provas = new ArrayList<>(provaDao.list());

		ModelAndView mv = new ModelAndView("admin/libera-prova");
		mv.addObject("usuarios", usuarios);
		mv.addObject("provas", provas);

		return mv;

	}

	@Transactional
	@RequestMapping("admin/salva-liberacao")
	public ModelAndView salvaLiberacao(
			@RequestParam("provas") List<Long> provasId,
			@RequestParam("usuarios") List<String> usuarios) {

		List<Prova> provas = new ArrayList<>(
				provaDao.getProvasPorIds(provasId));

		 if(provasId.isEmpty() || usuarios.isEmpty()){
		 ModelAndView mv = new ModelAndView("redirect:libera-prova");
		 mv.addObject("validacao", false);
		 return mv;
		 }

		for (String user : usuarios) {
			usuarioDao.salvaProvasLiberadas(user, provas);
		}
		ModelAndView mv = new ModelAndView("admin/provas-liberadas");
		return mv;
	}

}