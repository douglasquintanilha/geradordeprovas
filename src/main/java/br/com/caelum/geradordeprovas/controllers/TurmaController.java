package br.com.caelum.geradordeprovas.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.caelum.geradordeprovas.dao.ProvaDao;
import br.com.caelum.geradordeprovas.dao.TurmaDao;
import br.com.caelum.geradordeprovas.dao.UsuarioDao;
import br.com.caelum.geradordeprovas.models.Prova;
import br.com.caelum.geradordeprovas.models.Turma;
import br.com.caelum.geradordeprovas.models.Usuario;

@Controller
@Scope("request")
public class TurmaController {

	private ProvaDao provaDao;
	private UsuarioDao usuarioDao;
	private TurmaDao turmaDao;
	private Usuario usuarioLogado;
	
	@Autowired
	public TurmaController(@Qualifier("usuarioLogado") Usuario usuarioLogado, TurmaDao turmaDao, ProvaDao provaDao, UsuarioDao usuarioDao) {
		this.provaDao = provaDao;
		this.usuarioLogado = usuarioLogado;
		this.turmaDao = turmaDao;
		this.usuarioDao = usuarioDao;
	}

	@RequestMapping("/admin/turma")
	public ModelAndView criaTurmaView() {

		List<Prova> provas = new ArrayList<>(provaDao.list());
		List<Usuario> usuarios = new ArrayList<>(usuarioDao.list());
		ModelAndView mv = new ModelAndView("admin/cria-turma");

		mv.addObject("provas", provas);
		mv.addObject("usuarios", usuarios);

		return mv;
	}

	@Transactional
	@RequestMapping("/admin/criaTurma")
	public String criaTurma(@ModelAttribute("Turma") Turma turma, RedirectAttributes flash) {
		turma.atualizaProvasUsuarios();
		flash.addFlashAttribute("turma", turma);
		turmaDao.save(turma);
		return "redirect:turma";
	}

	@Transactional
	@RequestMapping("/matricula")
	public String matricula(@RequestParam("nomeTurma") String nomeTurma,
			RedirectAttributes flash) {
		Turma turma = turmaDao.procuraTurmaPor(nomeTurma);
		if (turma != null) {
			Usuario usuario = usuarioDao.atualiza(usuarioLogado);
			turma.adicionaUsuario(usuario);
			flash.addFlashAttribute("existeTurma", true);
			flash.addFlashAttribute("nomeTurma", nomeTurma);
		} else {
			flash.addFlashAttribute("existeTurma", false);
		}
		return "redirect:/liberadas";
	}

}
