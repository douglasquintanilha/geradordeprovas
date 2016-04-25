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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.caelum.geradordeprovas.dao.ProvaDao;
import br.com.caelum.geradordeprovas.dao.QuestaoDao;
import br.com.caelum.geradordeprovas.dao.TurmaDao;
import br.com.caelum.geradordeprovas.dao.UsuarioDao;
import br.com.caelum.geradordeprovas.models.Prova;
import br.com.caelum.geradordeprovas.models.Questao;
import br.com.caelum.geradordeprovas.models.Turma;
import br.com.caelum.geradordeprovas.models.Usuario;
import br.com.caelum.geradordeprovas.util.Criptografia;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private UsuarioDao usuarioDao;
	private Criptografia criptografia;
	private QuestaoDao questaoDao;
	private ProvaDao provaDao;
	private TurmaDao turmaDao;

	@Autowired
	public AdminController(ProvaDao provaDao, QuestaoDao questaoDao, UsuarioDao usuarioDao, Criptografia criptografia,
			TurmaDao turmaDao) {
		this.usuarioDao = usuarioDao;
		this.criptografia = criptografia;
		this.questaoDao = questaoDao;
		this.provaDao = provaDao;
		this.turmaDao = turmaDao;
	}

	@RequestMapping("/estatisticas")
	public ModelAndView estatisticas() {

		List<Questao> questoes = new ArrayList<>(questaoDao.list());
		return new ModelAndView("admin/estatisticas").addObject("questoes", questoes);

	}

	@RequestMapping("/turma")
	public ModelAndView criaTurmaView() {

		List<Prova> provas = new ArrayList<>(provaDao.list());
		List<Usuario> usuarios = new ArrayList<>(usuarioDao.list());
		ModelAndView mv = new ModelAndView("admin/cria-turma");

		mv.addObject("provas", provas);
		mv.addObject("usuarios", usuarios);

		return mv;
	}

	@Transactional
	@RequestMapping("/criaTurma")
	public String criaTurma(@ModelAttribute("Turma") Turma turma, RedirectAttributes flash) {
		
		turmaDao.save(turma);

		//nao consigo "flashear"
		return "redirect:turma";
	}

	@RequestMapping("/usuario/novo/form")
	public String criaUsuarioForm() {
		return "admin/cria-usuario-form";
	}

	@RequestMapping("/index")
	public String indexAdmin() {
		return "admin/index";
	}

	@Transactional
	@RequestMapping("/usuario/novo/salva")
	public String criaUsuario(@ModelAttribute("usuario") @Valid Usuario usuario, BindingResult result) {

		if (result.hasErrors() || usuarioDao.loginExistente(usuario.getLogin())) {
			if (usuarioDao.loginExistente(usuario.getLogin())) {
				result.rejectValue("login", "error.usuario", "Usuário já existente");
			}
			return "admin/cria-usuario-form";
		}

		String senhaCriptografada = criptografia.criptografaSenha(usuario.getSenha());
		usuario.setSenha(senhaCriptografada);
		usuarioDao.save(usuario);

		return "admin/usuario-adicionado";
	}

}