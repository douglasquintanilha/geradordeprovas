package com.caelum.geradordeprovas.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.caelum.geradordeprovas.DAO.UsuarioDao;
import com.caelum.geradordeprovas.models.Usuario;
import com.caelum.geradordeprovas.util.Criptografia;

@Controller
public class AdminController {

	private UsuarioDao usuarioDao;

	@Autowired
	public AdminController(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	@RequestMapping("admin/cria-usuario-form")
	public String criaUsuarioForm() {
		return "admin/cria-usuario-form";
	}

	@RequestMapping("admin/index")
	public String indexAdmin() {
		return "admin/index";
	}

	@org.springframework.transaction.annotation.Transactional
	@RequestMapping("admin/adiciona-usuario")
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

	@RequestMapping("novo-usuario-form")
	public String novoUsuarioForm() {
		return "novo-usuario-form";
	}

	@org.springframework.transaction.annotation.Transactional
	@RequestMapping("novo-usuario")
	public String novoUsuario(@ModelAttribute("usuario") @Valid Usuario usuario, BindingResult result) {

		if(result.hasErrors()){
			return "novo-usuario-form";
		}
		
		Criptografia crypt = new Criptografia();
		String senhaCript = crypt.criptografaSenha(usuario.getSenha());
		usuario.setSenha(senhaCript);
		usuario.setAdmin(false);
		
		usuarioDao.save(usuario);

		return "redirect:usuario-adicionado";
	}

	@RequestMapping("usuario-adicionado")
	public String usuarioAdicionado() {
		return "usuario-adicionado";
	}
}