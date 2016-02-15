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

import br.com.caelum.geradordeprovas.dao.EstatisticaQuestaoDao;
import br.com.caelum.geradordeprovas.dao.QuestaoDao;
import br.com.caelum.geradordeprovas.dao.UsuarioDao;
import br.com.caelum.geradordeprovas.models.Questao;
import br.com.caelum.geradordeprovas.models.Usuario;
import br.com.caelum.geradordeprovas.util.Criptografia;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private UsuarioDao usuarioDao;
	private Criptografia criptografia;
	private EstatisticaQuestaoDao estatisticaQuestaoDao;
	private QuestaoDao questaoDao;

	@Autowired
	public AdminController(QuestaoDao questaoDao, UsuarioDao usuarioDao,
			Criptografia criptografia, EstatisticaQuestaoDao estatisticaDao) {
		this.usuarioDao = usuarioDao;
		this.criptografia = criptografia;
		this.estatisticaQuestaoDao = estatisticaDao;
		this.questaoDao = questaoDao;
	}

	@RequestMapping("/estatisticas")
	public ModelAndView estatisticas() {

		List<Questao> questoes = new ArrayList<>(questaoDao.list());

		return new ModelAndView("admin/estatisticas").addObject("questoes",	questoes);
		
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
	public String criaUsuario(
			@ModelAttribute("usuario") @Valid Usuario usuario,
			BindingResult result) {

		if (result.hasErrors()) {
			return "admin/cria-usuario-form";
		}
		String senhaCriptografada = criptografia.criptografaSenha(usuario
				.getSenha());
		usuario.setSenha(senhaCriptografada);
		usuarioDao.save(usuario);

		return "admin/usuario-adicionado";
	}

}