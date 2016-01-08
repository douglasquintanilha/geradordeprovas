package br.com.caelum.geradordeprovas.controllers;

import javax.validation.Valid;

import org.apache.commons.codec.digest.Crypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.geradordeprovas.dao.UsuarioDao;
import br.com.caelum.geradordeprovas.models.Usuario;
import br.com.caelum.geradordeprovas.util.Criptografia;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private UsuarioDao usuarioDao;
	private Criptografia criptografia;

	@Autowired
	public AdminController(UsuarioDao usuarioDao,Criptografia criptografia) {
		this.usuarioDao = usuarioDao;
		this.criptografia = criptografia;
	}

	@RequestMapping("/estatisticas")
	public String estatisticas() {
		return "admin/estatisticas";
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
	public String criaUsuario(@ModelAttribute("usuario") @Valid Usuario usuario, BindingResult result){

		if(result.hasErrors()){
			return "admin/cria-usuario-form";
		}
		String senhaCriptografada = criptografia.criptografaSenha(usuario.getSenha());
		usuario.setSenha(senhaCriptografada);
		usuarioDao.save(usuario);

		return "admin/usuario-adicionado";
	}

}