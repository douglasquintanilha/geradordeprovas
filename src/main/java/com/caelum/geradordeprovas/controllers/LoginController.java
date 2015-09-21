package com.caelum.geradordeprovas.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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
		return "redirect:loginForm";
	}
	
	@RequestMapping("efetuaLogin")
	public ModelAndView efetuaLogin(@ModelAttribute("usuario") Usuario usuario,
			HttpSession sessao) {

		if (usuarioDao.validaUsuario(usuario)) {
			if(usuarioDao.ehAdmin(usuario)){
				sessao.setAttribute("adminLogado", usuario);
			}
			else{
				sessao.setAttribute("usuarioLogado", usuario);
			}
			System.out.println(usuario.isAdmin());
			ModelAndView mv = new ModelAndView( new RedirectView("/GeradorDeProvas/"));
			mv.addObject("nomeUsuario", usuario.getLogin());
			return mv;
		} else {
			ModelAndView mv = new ModelAndView("loginForm");
			return mv;
		}

	}

}
