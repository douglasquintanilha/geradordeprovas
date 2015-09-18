package com.caelum.geradordeprovas.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.caelum.geradordeprovas.DAO.UsuarioDao;
import com.caelum.geradordeprovas.models.Usuario;

@Controller
public class LoginController {

	private UsuarioDao usuarioDao;

	@Autowired
	public LoginController(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	@RequestMapping("loginForm")
	public String loginForm() {
		return "loginForm";
	}

	@RequestMapping("logout")
	public String logout(HttpSession sessao) {
		sessao.invalidate();
		return "loginForm";
	}
	
	@RequestMapping("efetuaLogin")
	public ModelAndView efetuaLogin(@ModelAttribute("usuario") Usuario usuario,
			HttpSession sessao) {

		if (usuarioDao.existeUsuario(usuario)) {
			sessao.setAttribute("usuarioLogado", usuario);
			System.out.println(sessao.getAttribute("usuarioLogado"));
			ModelAndView mv = new ModelAndView("index");
			mv.addObject("nomeUsuario", usuario.getLogin());
			return mv;
		} else {
			ModelAndView mv = new ModelAndView("loginForm");
			return mv;
		}

	}

}
