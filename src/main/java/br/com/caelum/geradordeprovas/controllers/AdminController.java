package br.com.caelum.geradordeprovas.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.geradordeprovas.dao.ProvaDao;
import br.com.caelum.geradordeprovas.dao.RelatorioUsuarioDao;
import br.com.caelum.geradordeprovas.dao.UsuarioDao;
import br.com.caelum.geradordeprovas.models.Prova;
import br.com.caelum.geradordeprovas.models.RelatorioUsuario;
import br.com.caelum.geradordeprovas.models.Usuario;
import br.com.caelum.geradordeprovas.services.PopulaBancoService;
import br.com.caelum.geradordeprovas.util.Criptografia;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private UsuarioDao usuarioDao;
	private Criptografia criptografia;
	private RelatorioUsuarioDao relatorioUsuarioDao;
	private ProvaDao provaDao;
	private PopulaBancoService populador;

	@Autowired
	public AdminController(PopulaBancoService populador, UsuarioDao usuarioDao, Criptografia criptografia,
			RelatorioUsuarioDao relatorioUsuarioDao, ProvaDao provaDao) {
		this.usuarioDao = usuarioDao;
		this.populador = populador;
		this.criptografia = criptografia;
		this.relatorioUsuarioDao = relatorioUsuarioDao;
		this.provaDao = provaDao;
	}

	@Transactional
	@RequestMapping("/populaBanco")
	public void populaBanco() {
		populador.popula();
	}

	@RequestMapping("/estatisticas")
	public ModelAndView estatisticas() {
		List<Prova> provas = provaDao.list();
		return new ModelAndView("admin/estatisticas").addObject("provas", provas);
	}

	@RequestMapping("/relatorios/{id}")
	public ModelAndView relatorios(@PathVariable Long id) {
		List<Prova> provas = provaDao.list();
		List<RelatorioUsuario> relatorios = new ArrayList<>();
		for (Prova prova : provas) {
			relatorios.addAll(relatorioUsuarioDao.findBy(prova));
		}

		ModelAndView mv = new ModelAndView("admin/relatorios");
		mv.addObject("relatorios", relatorios);

		return mv;
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