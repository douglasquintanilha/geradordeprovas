package br.com.caelum.geradordeprovas.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.geradordeprovas.dao.QuestaoDao;
import br.com.caelum.geradordeprovas.dao.RelatorioUsuarioDao;
import br.com.caelum.geradordeprovas.dao.UsuarioDao;
import br.com.caelum.geradordeprovas.models.RelatorioUsuario;
import br.com.caelum.geradordeprovas.models.Usuario;
import br.com.caelum.geradordeprovas.util.Criptografia;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private UsuarioDao usuarioDao;
	private Criptografia criptografia;
	private QuestaoDao questaoDao;
	private RelatorioUsuarioDao relatorioUsuarioDao;

	@Autowired
	public AdminController(QuestaoDao questaoDao, UsuarioDao usuarioDao, Criptografia criptografia, RelatorioUsuarioDao relatorioUsuarioDao) {
		this.usuarioDao = usuarioDao;
		this.criptografia = criptografia;
		this.questaoDao = questaoDao;
		this.relatorioUsuarioDao = relatorioUsuarioDao;
	}

	@RequestMapping("/estatisticas")
	public String estatisticas() {
		return "admin/estatisticas";
	}

	@RequestMapping("/relatorios")
	public ModelAndView relatorios() {
		List<RelatorioUsuario> relatorios = relatorioUsuarioDao.getRelatorioDeUma(1l);
		if(relatorios.isEmpty())
			System.out.println("ta empty");
		return new ModelAndView("admin/relatorios").addObject("relatorios", relatorios);
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