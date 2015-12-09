package br.com.caelum.geradordeprovas.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.geradordeprovas.dao.UsuarioDao;
import br.com.caelum.geradordeprovas.models.Usuario;
import br.com.caelum.geradordeprovas.util.Criptografia;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private UsuarioDao usuarioDao;

	@Autowired
	public AdminController(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView excecao(){
		return new ModelAndView("erro");
	}
	
	
	@RequestMapping("/usuario/novo/form")
	public String criaUsuarioForm() {
		return "admin/cria-usuario-form";
	}

	@RequestMapping("/index")
	public String indexAdmin() {
		return "admin/index";
	}

	@org.springframework.transaction.annotation.Transactional
	@RequestMapping("/usuario/novo/salva")
	public String criaUsuario(@ModelAttribute("usuario") @Valid Usuario usuario, BindingResult result) {

		if(result.hasErrors()){
			return "admin/cria-usuario-form";
		}
		Criptografia crypt = new Criptografia();
		String senhaCriptografada = crypt.criptografaSenha(usuario.getSenha());
		usuario.setSenha(senhaCriptografada);
		usuarioDao.save(usuario);

		return "admin/usuario-adicionado";
	}

}