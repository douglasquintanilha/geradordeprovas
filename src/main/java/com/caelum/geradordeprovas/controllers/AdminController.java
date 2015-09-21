package com.caelum.geradordeprovas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.caelum.geradordeprovas.DAO.UsuarioDao;
import com.caelum.geradordeprovas.models.Usuario;

@Controller
public class AdminController {

	private UsuarioDao usuarioDao;

	@Autowired
	public AdminController(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}
	
	@RequestMapping("cria-usuario-form")
	public String criaUsuarioForm() {
		return "cria-usuario-form";
	}

	@org.springframework.transaction.annotation.Transactional
	@RequestMapping("adiciona-usuario")
	public String criaUsuario(@RequestParam("nome") String nome,
			@RequestParam("senha") String senha,
			@RequestParam("admin") boolean admin) {


		Usuario user = new Usuario();
		user.setAdmin(admin);
		user.setLogin(nome);
		user.setSenha(senha);
		
		usuarioDao.save(user);
		
		return "ok";
	}

}
